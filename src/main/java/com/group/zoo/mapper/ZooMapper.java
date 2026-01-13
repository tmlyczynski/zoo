package com.group.zoo.mapper;


import com.group.zoo.domain.entity.Enclosure;
import com.group.zoo.domain.entity.User;
import com.group.zoo.domain.entity.Zoo;
import com.group.zoo.dto.ZooDto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ZooMapper {

    @Mapping(target = "enclosureIds", source = "enclosures", qualifiedByName = "enclosureListToIds")
    @Mapping(target = "userIds", source = "users", qualifiedByName = "userListToIds")
    ZooDto toDto(Zoo zoo);

    @Mapping(target = "enclosures", ignore = true)
    @Mapping(target = "users", ignore = true)
    Zoo toEntity(ZooDto zooDto);

    @Named("enclosureListToIds")
    static List<Long> enclosureListToIds(List<Enclosure> enclosures) {
        if (enclosures == null) return null;
        return enclosures.stream().map(Enclosure::getId).toList();
    }

    @Named("userListToIds")
    static List<Long> userListToIds(List<User> users) {
        if (users == null) return null;
        return users.stream().map(User::getId).toList();
    }
}
