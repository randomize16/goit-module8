package ua.goit.dto;

import lombok.Data;
import ua.goit.model.Category;
import ua.goit.validation.IsUnique;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@IsUnique
@Data
public class ItemDto {
    private String category;
    private Long categoryId;
    private String name;
    private String description;
}
