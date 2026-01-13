package com.group.zoo.mapper;

import com.group.zoo.domain.entity.CleaningTask;
import com.group.zoo.dto.CleaningTaskDto;
import com.group.zoo.domain.entity.Enclosure;
import com.group.zoo.domain.entity.User;
import com.group.zoo.repository.EnclosureRepository;
import com.group.zoo.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Context;

@Mapper(componentModel = "spring")
public interface CleaningTaskMapper {
    @Mapping(target = "enclosureId", source = "enclosure.id")
    @Mapping(target = "userId", source = "user.id")
    CleaningTaskDto toDto(CleaningTask cleaningTask);

    @Mapping(target = "enclosure", source = "enclosureId", qualifiedByName = "enclosureIdToEnclosure")
    @Mapping(target = "user", source = "userId", qualifiedByName = "userIdToUser")
    CleaningTask toEntity(CleaningTaskDto cleaningTaskDto, @Context EnclosureRepository enclosureRepository, @Context UserRepository userRepository);

    @Named("enclosureIdToEnclosure")
    static Enclosure enclosureIdToEnclosure(Long enclosureId, @Context EnclosureRepository enclosureRepository) {
        if (enclosureId == null) return null;
        return enclosureRepository.findById(enclosureId).orElse(null);
    }

    @Named("userIdToUser")
    static User userIdToUser(Long userId, @Context UserRepository userRepository) {
        if (userId == null) return null;
        return userRepository.findById(userId).orElse(null);
    }
}
