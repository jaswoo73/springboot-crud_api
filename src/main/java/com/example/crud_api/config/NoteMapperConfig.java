package com.example.crud_api.config;

import com.example.crud_api.mapper.NoteMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoteMapperConfig {

    @Bean
    public NoteMapper noteMapper() {
        return Mappers.getMapper(NoteMapper.class);
    }
}