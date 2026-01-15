package com.group.zoo.mapper;

import com.group.zoo.domain.entity.User;
import com.group.zoo.domain.entity.Zoo;
import com.group.zoo.domain.enums.UserRole;
import com.group.zoo.dto.UserDto;
import com.group.zoo.repository.CleaningTaskRepository;
import com.group.zoo.repository.FeedingRepository;
import com.group.zoo.repository.ZooRepository;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-15T09:57:09+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260101-2150, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setFeedingIds( UserMapper.feedingListToIds( user.getFeedings() ) );
        userDto.setCleaningTaskIds( UserMapper.cleaningTaskListToIds( user.getCleanings() ) );
        userDto.setZooId( userZooId( user ) );
        userDto.setActive( user.getActive() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setId( user.getId() );
        userDto.setLastName( user.getLastName() );
        userDto.setLogin( user.getLogin() );
        if ( user.getRole() != null ) {
            userDto.setRole( user.getRole().name() );
        }

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto, ZooRepository zooRepository, FeedingRepository feedingRepository, CleaningTaskRepository cleaningTaskRepository) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setFeedings( UserMapper.feedingIdsToFeedings( userDto.getFeedingIds(), feedingRepository ) );
        user.setCleanings( UserMapper.cleaningTaskIdsToCleaningTasks( userDto.getCleaningTaskIds(), cleaningTaskRepository ) );
        user.setZoo( UserMapper.zooIdToZoo( userDto.getZooId(), zooRepository ) );
        user.setActive( userDto.getActive() );
        user.setFirstName( userDto.getFirstName() );
        user.setId( userDto.getId() );
        user.setLastName( userDto.getLastName() );
        user.setLogin( userDto.getLogin() );
        if ( userDto.getRole() != null ) {
            user.setRole( Enum.valueOf( UserRole.class, userDto.getRole() ) );
        }

        return user;
    }

    private Long userZooId(User user) {
        if ( user == null ) {
            return null;
        }
        Zoo zoo = user.getZoo();
        if ( zoo == null ) {
            return null;
        }
        Long id = zoo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
