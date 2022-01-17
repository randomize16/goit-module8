package ua.goit.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.goit.dto.CategoryDto;
import ua.goit.model.Category;
import ua.goit.repositories.CategoryRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Cacheable(value = "categories")
    public List<Category> getAll() {
        return repository.findAll();
    }

    public List<CategoryDto> getAllDto() {
        return getAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private CategoryDto convert(Category entity) {
        return modelMapper.map(entity, CategoryDto.class);
    }

    @CacheEvict(value = "categories", allEntries = true)
    public void update(CategoryDto dto) {
        System.out.println("asdasdasdasdasd");
    }

    @Transactional
    public void create(CategoryDto dto) {
        Category entity = modelMapper.map(dto, Category.class);
        if (StringUtils.hasText(dto.getParentName())) {
            Category parent = repository.findByName(dto.getParentName());
            if (parent != null) {
                entity.setParent(parent);
            }
        } else {
            entity.setParent(null);
        }
        repository.save(entity);
    }

    public Category get(Long id) {
        return repository.getById(id);
    }

    @Transactional
    public void uploadImage(Long id, MultipartFile file) {
        repository.findById(id)
                .ifPresent(category -> {
                    try {
                        category.setImage(file.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    repository.save(category);
                });
    }

    public byte[] getImage(Long id) {
        return repository.findById(id)
                .map(Category::getImage)
                .orElse(null);
    }
}
