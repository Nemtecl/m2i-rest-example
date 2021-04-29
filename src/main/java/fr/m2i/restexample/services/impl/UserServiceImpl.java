package fr.m2i.restexample.services.impl;

import fr.m2i.restexample.dtos.RegisterDto;
import fr.m2i.restexample.dtos.UserDto;
import fr.m2i.restexample.exceptions.M2I400Exception;
import fr.m2i.restexample.exceptions.M2I404Exception;
import fr.m2i.restexample.repositories.ProductRepository;
import fr.m2i.restexample.repositories.RoleRepository;
import fr.m2i.restexample.repositories.UserRepository;
import fr.m2i.restexample.repositories.entities.Role;
import fr.m2i.restexample.repositories.entities.User;
import fr.m2i.restexample.services.UserService;
import fr.m2i.restexample.transformators.ProductTransformator;
import fr.m2i.restexample.transformators.UserTransformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserTransformator userTransformator;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           UserTransformator userTransformator, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userTransformator = userTransformator;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDto create(RegisterDto dto) throws M2I400Exception, M2I404Exception {
        Optional<User> optional = userRepository.findByUsername(dto.getUsername());
        if (optional.isPresent()) {
            throw new M2I400Exception("L'utilisateur " + dto.getUsername() + " existe déjà");
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new M2I400Exception("Les mots de passes ne corespondent pas");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new M2I404Exception("Le rôle USER n'existe pas"));

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(bcryptEncoder.encode(dto.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));

        return userTransformator.modelToDto(userRepository.save(user));
    }
}
