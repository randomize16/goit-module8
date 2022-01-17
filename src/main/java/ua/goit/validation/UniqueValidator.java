package ua.goit.validation;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<IsUnique, Object> {

	private String table;
	private String field;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void initialize(IsUnique constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.table = constraintAnnotation.table();
		Class<?> model = constraintAnnotation.model();
		Table tableAnnotation = model.getDeclaredAnnotation(Table.class);
		if (tableAnnotation != null) {
			this.table = tableAnnotation.name();
		}
	}

	@Override
	public boolean isValid(Object dto,
						   ConstraintValidatorContext context) {
		Object fieldValue = null;
		try {
			Field field = dto.getClass().getDeclaredField(this.field);
			field.setAccessible(true);
			fieldValue = field.get(dto);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		if (fieldValue != null) {
			Query query = em.createNativeQuery("select count(*) > 0 from " + this.table +
					 " where " + this.field + " = :param");
			query.setParameter("param", fieldValue);
			Boolean isPresentInDb = (Boolean) query.getSingleResult();
			if (isPresentInDb) {
				context.buildConstraintViolationWithTemplate("This field not unique")
						.addPropertyNode(this.field)
						.addConstraintViolation();
			}
			return !isPresentInDb;
		}

		return true;
	}
}
