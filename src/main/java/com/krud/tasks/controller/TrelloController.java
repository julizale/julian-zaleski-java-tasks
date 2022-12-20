package com.krud.tasks.controller;

import com.krud.tasks.client.TrelloClient;
import com.krud.tasks.domain.CreatedTrelloCard;
import com.krud.tasks.domain.TrelloBoardDto;
import com.krud.tasks.domain.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
         //       .filter(t -> t.getName().contains("Kodilla"))
                .forEach(trelloBoardDto-> {
                    System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
                    System.out.println("This board contains lists: ");
                    trelloBoardDto.getLists().forEach(trelloList ->
                            System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.getIsClosed()));
                });
    }

    @PostMapping("cards")
    public CreatedTrelloCard creteTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}
