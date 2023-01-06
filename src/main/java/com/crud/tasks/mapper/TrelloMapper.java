package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrelloMapper {

    public TrelloBoard mapToBoard(final TrelloBoardDto trelloBoardDto) {
        return new TrelloBoard(trelloBoardDto.getId(), trelloBoardDto.getName(), mapToList(trelloBoardDto.getLists()));
    }

    public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDtos) {
        return trelloListDtos.stream()
                .map(trelloListDto -> new TrelloList(trelloListDto.getId(), trelloListDto.getName(), trelloListDto.getIsClosed()))
                .collect(Collectors.toList());
    }

    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDtos) {
        return trelloBoardDtos.stream()
                .map(this::mapToBoard)
                .collect(Collectors.toList());
    }

    public List<TrelloListDto> mapToListsDto(final List<TrelloList> trelloList) {
        return trelloList.stream()
                .map(t -> new TrelloListDto(t.getId(), t.getName(), t.getIsClosed()))
                .collect(Collectors.toList());
    }

    public List<TrelloBoardDto> mapToBoardDtos(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(t -> new TrelloBoardDto(t.getName(), t.getId(), mapToListsDto(t.getLists())))
                .collect(Collectors.toList());
    }

    public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getPos(), trelloCard.getDescription(), trelloCard.getListId());
    }

    public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId());
    }
}
