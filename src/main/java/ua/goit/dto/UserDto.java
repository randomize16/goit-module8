package ua.goit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import ua.goit.validation.IsUnique;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@ToString
@IsUnique(table = "users", field = "name")
public class UserDto {
    @Size(min = 5)
    private String name;
    private String description;
    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
