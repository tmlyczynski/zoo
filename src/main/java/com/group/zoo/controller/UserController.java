package com.group.zoo.controller;

import lombok.RequiredArgsConstructor;

import com.group.zoo.domain.entity.User;
import com.group.zoo.domain.enums.UserRole;
import com.group.zoo.dto.UserDto;
import com.group.zoo.mapper.UserMapper;
import com.group.zoo.repository.UserRepository;
import com.group.zoo.repository.ZooRepository;
import com.group.zoo.repository.FeedingRepository;
import com.group.zoo.repository.CleaningTaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ZooRepository zooRepository;
    private final FeedingRepository feedingRepository;
    private final CleaningTaskRepository cleaningTaskRepository;

    @GetMapping
    public Page<UserDto> getAll(@RequestParam(required = false) UserRole userRole, Pageable pageable) {
        Page<User> users;
        if (userRole != null) {
            users = userRepository.findByUserRole(userRole, pageable);
        } else {
            users = userRepository.findAll(pageable);
        }
        return users.map(userMapper::toDto);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto dto) {
        User entity = userMapper.toEntity(dto, zooRepository, feedingRepository, cleaningTaskRepository);
        return userMapper.toDto(userRepository.save(entity));
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto dto) {
        User entity = userMapper.toEntity(dto, zooRepository, feedingRepository, cleaningTaskRepository);
        entity.setId(id);
        return userMapper.toDto(userRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
