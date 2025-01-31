package com.harun.usermanagementservice.mapper;

import org.mapstruct.factory.Mappers;

public class MapperGeneratorSingleton {

    private MapperGeneratorSingleton() {
    }

    public static final MapperGenerator INSTANCE = Mappers.getMapper(MapperGenerator.class);
}
