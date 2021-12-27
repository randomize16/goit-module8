package ua.goit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.goit.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}
