package com.krud.tasks.controller;

import com.krud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    //private TaskMapper taskMapper;
    //private DbService dbService;

    @GetMapping
    List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{taskId}")
    TaskDto getTask(Long taskId) {
        return new TaskDto(1L, "test title", "test_content");
    }

    @DeleteMapping
    void deleteTask(Long taskId) {

    }

    @PutMapping
    TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping
    void createTask(TaskDto taskDto) {

    }

}
