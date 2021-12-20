package ua.goit.dto;

import lombok.Data;
import lombok.ToString;
import ua.goit.validation.UserValidation;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@ToString
@UserValidation
public class UserDto {
    @Size(min = 5)
    private String name;
    @NotEmpty
    private String description;
    private String password;
}
