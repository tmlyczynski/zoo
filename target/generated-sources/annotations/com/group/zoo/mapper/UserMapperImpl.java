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
    date = "2026-01-14T11:14:52+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
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
        userDto.setId( user.getId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        if ( user.getRole() != null ) {
            userDto.setRole( user.getRole().name() );
        }
        userDto.setLogin( user.getLogin() );
        userDto.setActive( user.getActive() );

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
        user.setId( userDto.getId() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        if ( userDto.getRole() != null ) {
            user.setRole( Enum.valueOf( UserRole.class, userDto.getRole() ) );
        }
        user.setLogin( userDto.getLogin() );
        user.setActive( userDto.getActive() );

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
