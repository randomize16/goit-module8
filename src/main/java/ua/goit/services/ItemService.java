package ua.goit.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.dto.ItemDto;
import ua.goit.model.Category;
import ua.goit.model.Item;
import ua.goit.repositories.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModelMapper mapper;

    @Cacheable("items")
    public List<Item> getAll() {
        return repository.findAll();
    }

    @Cacheable(value = "items", key = "#id")
    public Item get(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Transactional
    @CacheEvict(value = "items", allEntries = true)
    public void save(ItemDto dto) {
        Item item = mapper.map(dto, Item.class);

        Category category = categoryService.get(dto.getCategoryId());
        if (category != null) {
            item.setCategory(category);
        } else {
            item.setCategory(null);
        }
        item.setId(null);
        repository.save(item);
    }
}
