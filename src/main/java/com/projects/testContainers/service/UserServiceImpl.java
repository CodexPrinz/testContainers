package com.projects.testContainers.service;

import com.projects.testContainers.db.entity.User;
import com.projects.testContainers.db.repository.UsersRepository;
import com.projects.testContainers.exception.UserNotFoundException;
import com.projects.testContainers.model.UserDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        User request = new User();
        BeanUtils.copyProperties(userDto, request);
        User user = this.usersRepository.saveAndFlush(request);
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public UserDto findUserByEmail(String email) {
        UserDto userDto = new UserDto();
        User user = this.usersRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

}
