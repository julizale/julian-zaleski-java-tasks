package com.krud.tasks.controller;

import com.krud.tasks.client.TrelloClient;
import com.krud.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public void getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        trelloBoards.stream()
                .filter(t-> t.getId() != null && t.getName() != null)
                .filter(t-> !t.getName().isEmpty() && !t.getId().isEmpty())
                .filter(t -> t.getName().contains("Kodilla"))
                .forEach(t-> System.out.println(t.getId() + " " + t.getName()));
    }
}
