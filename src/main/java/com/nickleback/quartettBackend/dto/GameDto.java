package com.nickleback.quartettBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class GameDto {

    private UUID uuid;

    private Long maxPlayers;

    private List<UserDto> playingUsers;

}
