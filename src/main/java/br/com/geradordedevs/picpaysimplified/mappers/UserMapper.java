package br.com.geradordedevs.picpaysimplified.mappers;

import br.com.geradordedevs.picpaysimplified.dtos.responses.UserResponseDTO;
import br.com.geradordedevs.picpaysimplified.dtos.requests.UserRequestDTO;
import br.com.geradordedevs.picpaysimplified.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    public UserEntity toEntity(UserRequestDTO userRequestDTO){
        log.info("converting dto{} to entity", userRequestDTO);
        return mapper.map(userRequestDTO,UserEntity.class);
    }

    public UserResponseDTO toDto(UserEntity userEntity){
        log.info("converting entity{} to dto", userEntity);
        return mapper.map(userEntity,UserResponseDTO.class);
    }
}
