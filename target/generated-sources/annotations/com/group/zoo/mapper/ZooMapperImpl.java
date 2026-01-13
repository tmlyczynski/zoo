package com.group.zoo.mapper;

import com.group.zoo.domain.entity.Zoo;
import com.group.zoo.dto.ZooDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-14T11:14:52+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class ZooMapperImpl implements ZooMapper {

    @Override
    public ZooDto toDto(Zoo zoo) {
        if ( zoo == null ) {
            return null;
        }

        ZooDto zooDto = new ZooDto();

        zooDto.setEnclosureIds( ZooMapper.enclosureListToIds( zoo.getEnclosures() ) );
        zooDto.setUserIds( ZooMapper.userListToIds( zoo.getUsers() ) );
        zooDto.setId( zoo.getId() );
        zooDto.setName( zoo.getName() );
        zooDto.setCity( zoo.getCity() );
        zooDto.setCountry( zoo.getCountry() );
        zooDto.setOpenFrom( zoo.getOpenFrom() );
        zooDto.setOpenTo( zoo.getOpenTo() );

        return zooDto;
    }

    @Override
    public Zoo toEntity(ZooDto zooDto) {
        if ( zooDto == null ) {
            return null;
        }

        Zoo zoo = new Zoo();

        zoo.setId( zooDto.getId() );
        zoo.setName( zooDto.getName() );
        zoo.setCity( zooDto.getCity() );
        zoo.setCountry( zooDto.getCountry() );
        zoo.setOpenFrom( zooDto.getOpenFrom() );
        zoo.setOpenTo( zooDto.getOpenTo() );

        return zoo;
    }
}
