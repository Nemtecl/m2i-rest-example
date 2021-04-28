package fr.m2i.restexample.services;

import fr.m2i.restexample.dtos.RegisterDto;
import fr.m2i.restexample.dtos.UserDto;
import fr.m2i.restexample.exceptions.M2I400Exception;

public interface UserService {
    UserDto create(RegisterDto dto) throws M2I400Exception;
}
