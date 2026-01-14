package com.group.zoo.controller;

import com.group.zoo.domain.entity.CleaningTask;
import com.group.zoo.dto.CleaningTaskDto;
import com.group.zoo.mapper.CleaningTaskMapper;
import com.group.zoo.repository.CleaningTaskRepository;
import com.group.zoo.repository.EnclosureRepository;
import com.group.zoo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cleaning-tasks")
public class CleaningTaskController {
    private final CleaningTaskRepository cleaningTaskRepository;
    private final CleaningTaskMapper cleaningTaskMapper;
    private final EnclosureRepository enclosureRepository;
    private final UserRepository userRepository;

    public CleaningTaskController(CleaningTaskRepository cleaningTaskRepository, CleaningTaskMapper cleaningTaskMapper, EnclosureRepository enclosureRepository, UserRepository userRepository) {
        this.cleaningTaskRepository = cleaningTaskRepository;
        this.cleaningTaskMapper = cleaningTaskMapper;
        this.enclosureRepository = enclosureRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<CleaningTaskDto> getAll() {
        return cleaningTaskRepository.findAll().stream()
                .map(cleaningTaskMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CleaningTaskDto getById(@PathVariable Long id) {
        return cleaningTaskRepository.findById(id)
                .map(cleaningTaskMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public CleaningTaskDto create(@RequestBody CleaningTaskDto dto) {
        CleaningTask entity = cleaningTaskMapper.toEntity(dto, enclosureRepository, userRepository);
        return cleaningTaskMapper.toDto(cleaningTaskRepository.save(entity));
    }

    @PutMapping("/{id}")
    public CleaningTaskDto update(@PathVariable Long id, @RequestBody CleaningTaskDto dto) {
        CleaningTask entity = cleaningTaskMapper.toEntity(dto, enclosureRepository, userRepository);
        entity.setId(id);
        return cleaningTaskMapper.toDto(cleaningTaskRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cleaningTaskRepository.deleteById(id);
    }
}
