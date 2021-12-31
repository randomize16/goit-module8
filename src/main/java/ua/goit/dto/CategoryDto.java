package ua.goit.dto;

import lombok.Data;
import lombok.ToString;
import ua.goit.validation.UniqueValidation;

@Data
@ToString
@UniqueValidation(table = "catetories", field = "name")
public class CategoryDto {
}
