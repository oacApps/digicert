package com.digicert.usermanagement.domain.mapper;

import com.digicert.usermanagement.domain.entity.UserEntity;
import com.digicert.usermanagement.domain.model.UserMdl;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(UserMdl user) throws ParseException;
    UserMdl toModel(UserEntity user) throws ParseException;
    List<UserMdl> toModelList(List<UserEntity> userEntityList) throws ParseException;
}
