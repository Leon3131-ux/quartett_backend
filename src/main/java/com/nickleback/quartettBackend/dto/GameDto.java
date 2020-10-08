package com.nickleback.quartettBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GameDto {

    private Long inviteCode;

    private List<UserDto> playingUsers;

}
