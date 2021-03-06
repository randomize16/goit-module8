package ua.goit.dto;

import lombok.Data;
import lombok.ToString;
import ua.goit.model.Category;
import ua.goit.validation.IsUnique;

@Data
@ToString
@IsUnique(field = "name", model = Category.class)
public class CategoryDto {
    private String name;
    private String description;
    private String parentName;
}
