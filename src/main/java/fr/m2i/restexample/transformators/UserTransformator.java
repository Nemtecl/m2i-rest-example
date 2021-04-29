package fr.m2i.restexample.transformators;

import fr.m2i.restexample.dtos.UserDto;
import fr.m2i.restexample.repositories.entities.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserTransformator {
    public UserDto modelToDto(final User product) {
        if (product == null) {
            return null;
        }
        return new UserDto(product.getId(), product.getUsername());
    }

    public List<UserDto> modelsToDtos(final List<User> models) {
        return models.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public User dtoToModel(final UserDto dto) {
        if (dto == null) {
            return null;
        }
        return new User(dto.getId(), dto.getUsername(), null, new HashSet<>());
    }

    public List<User> dtosToModels(final List<UserDto> dtos) {
        return dtos.stream().map(this::dtoToModel).collect(Collectors.toList());
    }

}
