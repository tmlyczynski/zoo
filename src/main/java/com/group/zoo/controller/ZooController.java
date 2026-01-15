package com.group.zoo.controller;

import lombok.RequiredArgsConstructor;

import com.group.zoo.domain.entity.Zoo;
import com.group.zoo.dto.ZooDto;
import com.group.zoo.mapper.ZooMapper;
import com.group.zoo.repository.ZooRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/zoos")
public class ZooController {
    private final ZooRepository zooRepository;
    private final ZooMapper zooMapper;

    @GetMapping
    public Page<ZooDto> getAll(Pageable pageable) {
        return zooRepository.findAllByDeletedFalse(pageable)
                .map(zooMapper::toDto);
    }

    @GetMapping("/{id}")
    public ZooDto getById(@PathVariable Long id) {
        return zooRepository.findByIdAndDeletedFalse(id)
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
        zooRepository.findById(id).ifPresent(zoo -> {
            zoo.setDeleted(true);
            zooRepository.save(zoo);
        });
    }
}
