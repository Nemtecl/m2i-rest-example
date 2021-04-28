package fr.m2i.restexample.services.impl;

import fr.m2i.restexample.dtos.RegisterDto;
import fr.m2i.restexample.dtos.UserDto;
import fr.m2i.restexample.exceptions.M2I400Exception;
import fr.m2i.restexample.repositories.ProductRepository;
import fr.m2i.restexample.repositories.UserRepository;
import fr.m2i.restexample.repositories.entities.User;
import fr.m2i.restexample.services.UserService;
import fr.m2i.restexample.transformators.ProductTransformator;
import fr.m2i.restexample.transformators.UserTransformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTransformator userTransformator;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserTransformator userTransformator,
                           PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.userTransformator = userTransformator;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDto create(RegisterDto dto) throws M2I400Exception {
        Optional<User> optional = userRepository.findByUsername(dto.getUsername());
        if (optional.isPresent()) {
            throw new M2I400Exception("L'utilisateur " + dto.getUsername() + " existe déjà");
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new M2I400Exception("Les mots de passes ne corespondent pas");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(bcryptEncoder.encode(dto.getPassword()));

        return userTransformator.modelToDto(userRepository.save(user));
    }
}
