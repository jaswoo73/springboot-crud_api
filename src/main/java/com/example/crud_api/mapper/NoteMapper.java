package com.example.crud_api.mapper;

import com.example.crud_api.dto.NoteDTO;
import com.example.crud_api.dto.NoteRequestDTO;
import com.example.crud_api.model.NoteServiceModel;
import com.example.crud_api.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface NoteMapper {

    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    @Mapping(target = "id", ignore = true)
    Note toEntity(NoteServiceModel serviceModel);

    NoteDTO toDTO(Note note);

    NoteServiceModel toServiceModel(NoteRequestDTO noteRequestDTO);
}
