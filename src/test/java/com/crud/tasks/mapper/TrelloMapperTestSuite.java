package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    void testMapToBoard() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "name", true);
        List<TrelloListDto> list = new ArrayList<>();
        list.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("name", "1", list);
        //When
        TrelloBoard board = trelloMapper.mapToBoard(trelloBoardDto);
        //Then
        assertEquals("name", board.getName());
        assertEquals("1", board.getId());
        assertEquals(1, board.getLists().size());
    }

    @Test
    void testMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "name", true);
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(trelloListDto);
        //When
        List<TrelloList> list = trelloMapper.mapToList(listDtos);
        //Then
        assertEquals(1, list.size());
        assertEquals("1", list.get(0).getId());
        assertEquals("name", list.get(0).getName());
        assertTrue(list.get(0).getIsClosed());
    }

    @Test
    void testMapToBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "name", true);
        List<TrelloListDto> list = new ArrayList<>();
        list.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("name", "1", list);
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(1, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("name", trelloBoards.get(0).getName());
        assertEquals(1, trelloBoards.get(0).getLists().size());
    }

    @Test
    void testMapToListsDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "name", true);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloList);
        //When
        List<TrelloListDto> listDtos = trelloMapper.mapToListsDto(lists);
        //Then
        assertEquals(1, listDtos.size());
        assertEquals("1", listDtos.get(0).getId());
        assertEquals("name", listDtos.get(0).getName());
        assertTrue(listDtos.get(0).getIsClosed());
    }

    @Test
    void testMapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "name", true);
        List<TrelloList> list = new ArrayList<>();
        list.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("1", "name", list);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard);
        //When
        List<TrelloBoardDto> trelloBoards = trelloMapper.mapToBoardDtos(trelloBoardList);
        //Then
        assertEquals(1, trelloBoards.size());
        assertEquals("1", trelloBoards.get(0).getId());
        assertEquals("name", trelloBoards.get(0).getName());
        assertEquals(1, trelloBoards.get(0).getLists().size());
    }

    @Test
    void testmapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "des", "pos", "12");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("12", trelloCardDto.getListId());
        assertEquals("pos", trelloCardDto.getPos());
        assertEquals("name", trelloCardDto.getName());
        assertEquals("des", trelloCardDto.getDescription());
    }

    @Test
    void testmapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "pos", "des", "12");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("12", trelloCard.getListId());
        assertEquals("pos", trelloCard.getPos());
        assertEquals("name", trelloCard.getName());
        assertEquals("des", trelloCard.getDescription());
    }
}


