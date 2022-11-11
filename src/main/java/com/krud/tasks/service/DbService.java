package com.krud.tasks.service;

import com.krud.tasks.controller.TaskNotFoundException;
import com.krud.tasks.domain.Task;
import com.krud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTask(long id) throws TaskNotFoundException {
        return repository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    public Task save(final Task task) {
        return repository.save(task);
    }

}
