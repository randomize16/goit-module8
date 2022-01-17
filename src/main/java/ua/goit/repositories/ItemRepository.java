package ua.goit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
