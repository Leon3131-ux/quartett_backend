package com.nickleback.quartettBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GameDto {
    private Long id;

    private String inviteCode;

    private List<UserDto> playingUsers;

    private Long remainingTime;
}
