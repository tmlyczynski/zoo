package com.group.zoo.controller;

import com.group.zoo.domain.entity.Zoo;
import com.group.zoo.dto.ZooDto;
import com.group.zoo.mapper.ZooMapper;
import com.group.zoo.repository.ZooRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/zoos")
public class ZooController {
    private final ZooRepository zooRepository;
    private final ZooMapper zooMapper;

    public ZooController(ZooRepository zooRepository, ZooMapper zooMapper) {
        this.zooRepository = zooRepository;
        this.zooMapper = zooMapper;
    }

    @GetMapping
    public List<ZooDto> getAll() {
        return zooRepository.findAll().stream()
                .map(zooMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ZooDto getById(@PathVariable Long id) {
        return zooRepository.findById(id)
                .map(zooMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public ZooDto create(@RequestBody ZooDto dto) {
        Zoo entity = zooMapper.toEntity(dto);
        return zooMapper.toDto(zooRepository.save(entity));
    }

    @PutMapping("/{id}")
    public ZooDto update(@PathVariable Long id, @RequestBody ZooDto dto) {
        Zoo entity = zooMapper.toEntity(dto);
        entity.setId(id);
        return zooMapper.toDto(zooRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        zooRepository.deleteById(id);
    }
}
