package com.group.zoo.controller;

import lombok.RequiredArgsConstructor;

import com.group.zoo.domain.entity.CleaningTask;
import com.group.zoo.dto.CleaningTaskDto;
import com.group.zoo.mapper.CleaningTaskMapper;
import com.group.zoo.repository.CleaningTaskRepository;
import com.group.zoo.repository.EnclosureRepository;
import com.group.zoo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cleaning-tasks")
public class CleaningTaskController {
    private final CleaningTaskRepository cleaningTaskRepository;
    private final CleaningTaskMapper cleaningTaskMapper;
    private final EnclosureRepository enclosureRepository;
    private final UserRepository userRepository;

    @GetMapping
    public Page<CleaningTaskDto> getAll(Pageable pageable) {
        return cleaningTaskRepository.findAll(pageable)
                .map(cleaningTaskMapper::toDto);
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
