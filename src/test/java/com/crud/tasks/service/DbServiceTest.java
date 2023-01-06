package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private DbService dbService;

    @Test
    void shouldFetchEmptyList() {
        //Given
        when(taskRepository.findAll()).thenReturn(List.of());

        //When
        List<Task> retrievedTaskList = dbService.getAllTasks();
        //Then
        assertNotNull(retrievedTaskList);
        assertEquals(0, retrievedTaskList.size());
    }

    @Test
    void testDbServiceThrows() {
        //Given
        when(taskRepository.findById(any())).thenReturn(null);
        when(taskRepository.existsById(any())).thenReturn(false);
        //When & Then
        assertThrows(Exception.class, () -> dbService.getTask(120));
        assertThrows(Exception.class, () -> dbService.deleteById(120));
    }
}
