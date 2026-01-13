package com.group.zoo.mapper;

import com.group.zoo.domain.entity.Feeding;
import com.group.zoo.domain.entity.Food;
import com.group.zoo.dto.FoodDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Context;
import java.util.List;
import com.group.zoo.repository.FeedingRepository;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    @Mapping(target = "feedingIds", source = "feedings", qualifiedByName = "feedingListToIds")
    FoodDto toDto(Food food);

    @Mapping(target = "feedings", source = "feedingIds", qualifiedByName = "feedingIdsToFeedings")
    Food toEntity(FoodDto foodDto, @Context FeedingRepository feedingRepository);

    @Named("feedingListToIds")
    static List<Long> feedingListToIds(List<Feeding> feedings) {
        if (feedings == null) return null;
        return feedings.stream().map(Feeding::getId).toList();
    }

    @Named("feedingIdsToFeedings")
    static List<Feeding> feedingIdsToFeedings(List<Long> feedingIds, @Context FeedingRepository feedingRepository) {
        if (feedingIds == null) return null;
        return feedingIds.stream().map(id -> feedingRepository.findById(id).orElse(null)).filter(java.util.Objects::nonNull).toList();
    }
}
