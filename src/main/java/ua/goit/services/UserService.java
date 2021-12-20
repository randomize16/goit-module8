package ua.goit.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.dto.UserDto;
import ua.goit.model.User;
import ua.goit.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public List<UserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto get(Long id) {
        return repository.findById(id)
                .map(this::convertToDto)
                .orElseThrow();
    }

    public void create(UserDto userDto) {
        repository.save(modelMapper.map(userDto, User.class));
    }

    public void update(Long id, UserDto userDto) {
        repository.findById(id)
                .map(user -> {
                    user.setDescription(userDto.getDescription());
                    return user;
                }).ifPresent(user -> {
                    repository.save(user);
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
