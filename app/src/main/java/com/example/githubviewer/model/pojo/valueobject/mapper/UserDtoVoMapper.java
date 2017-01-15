package com.example.githubviewer.model.pojo.valueobject.mapper;

import com.example.githubviewer.model.pojo.datatransferobject.UserDto;
import com.example.githubviewer.model.pojo.valueobject.UserVo;

import rx.functions.Func1;

public class UserDtoVoMapper implements Func1<UserDto, UserVo> {

    @Override
    public UserVo call(UserDto dto) {
        return UserVo.newBuilder()
                .id(dto.getId())
                .login(dto.getLogin())
                .avatarUrl(dto.getAvatarUrl())
                .type(dto.getType())
                .name(dto.getName())
                .company(dto.getCompany())
                .build();
    }
}
