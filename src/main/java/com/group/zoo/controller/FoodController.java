package com.group.zoo.controller;

import com.group.zoo.domain.entity.Food;
import com.group.zoo.dto.FoodDto;
import com.group.zoo.mapper.FoodMapper;
import com.group.zoo.repository.FoodRepository;
import com.group.zoo.repository.FeedingRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/foods")
public class FoodController {
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    private final FeedingRepository feedingRepository;

    public FoodController(FoodRepository foodRepository, FoodMapper foodMapper, FeedingRepository feedingRepository) {
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
        this.feedingRepository = feedingRepository;
    }

    @GetMapping
    public List<FoodDto> getAll() {
        return foodRepository.findAll().stream()
                .map(foodMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FoodDto getById(@PathVariable Long id) {
        return foodRepository.findById(id)
                .map(foodMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public FoodDto create(@RequestBody FoodDto dto) {
        Food entity = foodMapper.toEntity(dto, feedingRepository);
        return foodMapper.toDto(foodRepository.save(entity));
    }

    @PutMapping("/{id}")
    public FoodDto update(@PathVariable Long id, @RequestBody FoodDto dto) {
        Food entity = foodMapper.toEntity(dto, feedingRepository);
        entity.setId(id);
        return foodMapper.toDto(foodRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        foodRepository.deleteById(id);
    }
}
