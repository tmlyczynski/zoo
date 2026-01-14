package com.group.zoo.controller;

import com.group.zoo.domain.entity.User;
import com.group.zoo.dto.UserDto;
import com.group.zoo.mapper.UserMapper;
import com.group.zoo.repository.UserRepository;
import com.group.zoo.repository.ZooRepository;
import com.group.zoo.repository.FeedingRepository;
import com.group.zoo.repository.CleaningTaskRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ZooRepository zooRepository;
    private final FeedingRepository feedingRepository;
    private final CleaningTaskRepository cleaningTaskRepository;

    public UserController(UserRepository userRepository, UserMapper userMapper, ZooRepository zooRepository, FeedingRepository feedingRepository, CleaningTaskRepository cleaningTaskRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.zooRepository = zooRepository;
        this.feedingRepository = feedingRepository;
        this.cleaningTaskRepository = cleaningTaskRepository;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
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
