package com.group.zoo.mapper;

import com.group.zoo.domain.entity.CleaningTask;
import com.group.zoo.domain.entity.Enclosure;
import com.group.zoo.domain.entity.User;
import com.group.zoo.domain.enums.TaskStatus;
import com.group.zoo.dto.CleaningTaskDto;
import com.group.zoo.repository.EnclosureRepository;
import com.group.zoo.repository.UserRepository;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-14T11:14:52+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class CleaningTaskMapperImpl implements CleaningTaskMapper {

    @Override
    public CleaningTaskDto toDto(CleaningTask cleaningTask) {
        if ( cleaningTask == null ) {
            return null;
        }

        CleaningTaskDto cleaningTaskDto = new CleaningTaskDto();

        cleaningTaskDto.setEnclosureId( cleaningTaskEnclosureId( cleaningTask ) );
        cleaningTaskDto.setUserId( cleaningTaskUserId( cleaningTask ) );
        cleaningTaskDto.setId( cleaningTask.getId() );
        cleaningTaskDto.setPlannedAt( cleaningTask.getPlannedAt() );
        cleaningTaskDto.setDoneAt( cleaningTask.getDoneAt() );
        if ( cleaningTask.getStatus() != null ) {
            cleaningTaskDto.setStatus( cleaningTask.getStatus().name() );
        }

        return cleaningTaskDto;
    }

    @Override
    public CleaningTask toEntity(CleaningTaskDto cleaningTaskDto, EnclosureRepository enclosureRepository, UserRepository userRepository) {
        if ( cleaningTaskDto == null ) {
            return null;
        }

        CleaningTask cleaningTask = new CleaningTask();

        cleaningTask.setEnclosure( CleaningTaskMapper.enclosureIdToEnclosure( cleaningTaskDto.getEnclosureId(), enclosureRepository ) );
        cleaningTask.setUser( CleaningTaskMapper.userIdToUser( cleaningTaskDto.getUserId(), userRepository ) );
        cleaningTask.setId( cleaningTaskDto.getId() );
        cleaningTask.setPlannedAt( cleaningTaskDto.getPlannedAt() );
        cleaningTask.setDoneAt( cleaningTaskDto.getDoneAt() );
        if ( cleaningTaskDto.getStatus() != null ) {
            cleaningTask.setStatus( Enum.valueOf( TaskStatus.class, cleaningTaskDto.getStatus() ) );
        }

        return cleaningTask;
    }

    private Long cleaningTaskEnclosureId(CleaningTask cleaningTask) {
        if ( cleaningTask == null ) {
            return null;
        }
        Enclosure enclosure = cleaningTask.getEnclosure();
        if ( enclosure == null ) {
            return null;
        }
        Long id = enclosure.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long cleaningTaskUserId(CleaningTask cleaningTask) {
        if ( cleaningTask == null ) {
            return null;
        }
        User user = cleaningTask.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
