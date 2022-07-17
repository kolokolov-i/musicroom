package ru.superbro.musicroom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.superbro.musicroom.dto.PlaylistDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class PlaylistController {
    
    @GetMapping
    public List<PlaylistDto> playlists() {
        return Stream.of(new PlaylistDto("PL1", 101),
                         new PlaylistDto("PL2", 102))
                     .collect(Collectors.toList());
    }
    
}
