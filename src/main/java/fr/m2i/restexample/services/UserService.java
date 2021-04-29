package fr.m2i.restexample.services;

import fr.m2i.restexample.dtos.RegisterDto;
import fr.m2i.restexample.dtos.UserDto;
import fr.m2i.restexample.exceptions.M2I400Exception;
import fr.m2i.restexample.exceptions.M2I404Exception;

public interface UserService {
    UserDto create(RegisterDto dto) throws M2I400Exception, M2I404Exception;
}
