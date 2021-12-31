package ua.goit.validation;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValidation, Object> {

	private String table;
	private String field;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void initialize(UniqueValidation constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.table = constraintAnnotation.table();
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
			return !isPresentInDb;
		}

		return true;
	}
}
