package com.group.zoo.mapper;

import com.group.zoo.domain.entity.Animal;
import com.group.zoo.domain.entity.Feeding;
import com.group.zoo.domain.entity.Food;
import com.group.zoo.domain.entity.User;
import com.group.zoo.dto.FeedingDto;
import com.group.zoo.repository.AnimalRepository;
import com.group.zoo.repository.FoodRepository;
import com.group.zoo.repository.UserRepository;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-14T11:19:15+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260101-2150, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class FeedingMapperImpl implements FeedingMapper {

    @Override
    public FeedingDto toDto(Feeding feeding) {
        if ( feeding == null ) {
            return null;
        }

        FeedingDto feedingDto = new FeedingDto();

        feedingDto.setAnimalId( feedingAnimalId( feeding ) );
        feedingDto.setUserId( feedingUserId( feeding ) );
        feedingDto.setFoodId( feedingFoodId( feeding ) );
        feedingDto.setAmount( feeding.getAmount() );
        feedingDto.setFeedingTime( feeding.getFeedingTime() );
        feedingDto.setId( feeding.getId() );
        feedingDto.setNotes( feeding.getNotes() );

        return feedingDto;
    }

    @Override
    public Feeding toEntity(FeedingDto feedingDto, AnimalRepository animalRepository, UserRepository userRepository, FoodRepository foodRepository) {
        if ( feedingDto == null ) {
            return null;
        }

        Feeding feeding = new Feeding();

        feeding.setAnimal( FeedingMapper.animalIdToAnimal( feedingDto.getAnimalId(), animalRepository ) );
        feeding.setUser( FeedingMapper.userIdToUser( feedingDto.getUserId(), userRepository ) );
        feeding.setFood( FeedingMapper.foodIdToFood( feedingDto.getFoodId(), foodRepository ) );
        feeding.setAmount( feedingDto.getAmount() );
        feeding.setFeedingTime( feedingDto.getFeedingTime() );
        feeding.setId( feedingDto.getId() );
        feeding.setNotes( feedingDto.getNotes() );

        return feeding;
    }

    private Long feedingAnimalId(Feeding feeding) {
        if ( feeding == null ) {
            return null;
        }
        Animal animal = feeding.getAnimal();
        if ( animal == null ) {
            return null;
        }
        Long id = animal.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long feedingUserId(Feeding feeding) {
        if ( feeding == null ) {
            return null;
        }
        User user = feeding.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long feedingFoodId(Feeding feeding) {
        if ( feeding == null ) {
            return null;
        }
        Food food = feeding.getFood();
        if ( food == null ) {
            return null;
        }
        Long id = food.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
