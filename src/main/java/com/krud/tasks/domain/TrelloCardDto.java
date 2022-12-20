package com.krud.tasks.domain;

import lombok.Data;

@Data
public class TrelloCardDto {

    private String name;
    private String pos;
    private String description;
    private String listId;
}
