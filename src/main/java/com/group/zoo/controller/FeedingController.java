package com.group.zoo.controller;

import com.group.zoo.domain.entity.Feeding;
import com.group.zoo.dto.FeedingDto;
import com.group.zoo.mapper.FeedingMapper;
import com.group.zoo.repository.FeedingRepository;
import com.group.zoo.repository.AnimalRepository;
import com.group.zoo.repository.UserRepository;
import com.group.zoo.repository.FoodRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feedings")
public class FeedingController {
    private final FeedingRepository feedingRepository;
    private final FeedingMapper feedingMapper;
    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    public FeedingController(FeedingRepository feedingRepository, FeedingMapper feedingMapper, AnimalRepository animalRepository, UserRepository userRepository, FoodRepository foodRepository) {
        this.feedingRepository = feedingRepository;
        this.feedingMapper = feedingMapper;
        this.animalRepository = animalRepository;
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
    }

    @GetMapping
    public List<FeedingDto> getAll() {
        return feedingRepository.findAll().stream()
                .map(feedingMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FeedingDto getById(@PathVariable Long id) {
        return feedingRepository.findById(id)
                .map(feedingMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public FeedingDto create(@RequestBody FeedingDto dto) {
        Feeding entity = feedingMapper.toEntity(dto, animalRepository, userRepository, foodRepository);
        return feedingMapper.toDto(feedingRepository.save(entity));
    }

    @PutMapping("/{id}")
    public FeedingDto update(@PathVariable Long id, @RequestBody FeedingDto dto) {
        Feeding entity = feedingMapper.toEntity(dto, animalRepository, userRepository, foodRepository);
        entity.setId(id);
        return feedingMapper.toDto(feedingRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        feedingRepository.deleteById(id);
    }
}
