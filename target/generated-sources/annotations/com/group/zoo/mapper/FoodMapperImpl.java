package com.group.zoo.mapper;

import com.group.zoo.domain.entity.Food;
import com.group.zoo.domain.enums.DietType;
import com.group.zoo.dto.FoodDto;
import com.group.zoo.repository.FeedingRepository;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-15T11:24:02+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260101-2150, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class FoodMapperImpl implements FoodMapper {

    @Override
    public FoodDto toDto(Food food) {
        if ( food == null ) {
            return null;
        }

        FoodDto foodDto = new FoodDto();

        foodDto.setFeedingIds( FoodMapper.feedingListToIds( food.getFeedings() ) );
        foodDto.setId( food.getId() );
        foodDto.setName( food.getName() );
        if ( food.getDietType() != null ) {
            foodDto.setDietType( food.getDietType().name() );
        }
        foodDto.setCaloriesPerServing( food.getCaloriesPerServing() );

        return foodDto;
    }

    @Override
    public Food toEntity(FoodDto foodDto, FeedingRepository feedingRepository) {
        if ( foodDto == null ) {
            return null;
        }

        Food food = new Food();

        food.setFeedings( FoodMapper.feedingIdsToFeedings( foodDto.getFeedingIds(), feedingRepository ) );
        food.setId( foodDto.getId() );
        food.setName( foodDto.getName() );
        if ( foodDto.getDietType() != null ) {
            food.setDietType( Enum.valueOf( DietType.class, foodDto.getDietType() ) );
        }
        food.setCaloriesPerServing( foodDto.getCaloriesPerServing() );

        return food;
    }
}
