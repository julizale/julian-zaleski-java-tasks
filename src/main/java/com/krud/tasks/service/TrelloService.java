package com.krud.tasks.service;

import com.krud.tasks.config.AdminConfig;
import com.krud.tasks.domain.CreatedTrelloCardDto;
import com.krud.tasks.domain.Mail;
import com.krud.tasks.domain.TrelloBoardDto;
import com.krud.tasks.domain.TrelloCardDto;
import com.krud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class TrelloService {
    private final TrelloClient trelloClient;
    private final SimpleEmailService simpleEmailService;
    private final AdminConfig adminConfig;
    public static final String SUBJECT = "Tasks: New Trello card";

    public List<TrelloBoardDto> fetchTrelloBoards(){
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(TrelloCardDto trelloCardDto){
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        ofNullable(newCard).ifPresent(card -> simpleEmailService.send(
                new Mail(
                adminConfig.getAdminMail(),
                null,
                SUBJECT,
                "New card: " + trelloCardDto.getName() + " has been created on your Trello account"
        )));
        return newCard;
    }
}
