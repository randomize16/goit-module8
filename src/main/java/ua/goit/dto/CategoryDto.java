package ua.goit.dto;

import lombok.Data;
import lombok.ToString;
import ua.goit.model.Category;
import ua.goit.validation.UniqueValidation;

import javax.persistence.Column;

@Data
@ToString
@UniqueValidation(field = "name", model = Category.class)
public class CategoryDto {
    private String name;
    private String description;
    private String parentName;
}
