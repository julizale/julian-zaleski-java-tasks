package com.krud.tasks.scheduler;

import com.krud.tasks.config.AdminConfig;
import com.krud.tasks.domain.Mail;
import com.krud.tasks.domain.Task;
import com.krud.tasks.domain.TaskDto;
import com.krud.tasks.repository.TaskRepository;
import com.krud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    public final static String SUBJECT = "Tasks: Once a day email.";

    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail(){
        long size = taskRepository.count();
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                null,
                SUBJECT,
                "Currently, You have " + size + " tasks in database"
        ));
    }
}
