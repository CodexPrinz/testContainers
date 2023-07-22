package com.projects.testContainers.service;

import com.projects.testContainers.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface UserService {

    public UserDto createUser(UserDto userDto);

    public UserDto findUserByEmail(String email);
}
