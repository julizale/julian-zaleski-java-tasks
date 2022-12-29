package com.krud.tasks.controller;

import com.krud.tasks.trello.client.TrelloClient;
import com.krud.tasks.domain.CreatedTrelloCard;
import com.krud.tasks.domain.TrelloBoardDto;
import com.krud.tasks.domain.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public ResponseEntity<List<TrelloBoardDto>> getTrelloBoards() {
        return ResponseEntity.ok(trelloClient.getTrelloBoards());
    }

    @PostMapping("cards")
    public ResponseEntity<CreatedTrelloCard> createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return ResponseEntity.ok(trelloClient.createNewCard(trelloCardDto));
    }
}
