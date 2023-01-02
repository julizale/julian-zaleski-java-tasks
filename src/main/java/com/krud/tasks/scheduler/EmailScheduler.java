package com.krud.tasks.scheduler;

import com.krud.tasks.config.AdminConfig;
import com.krud.tasks.domain.Mail;
import com.krud.tasks.repository.TaskRepository;
import com.krud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
        String taskOrTasks = size != 1 ? " tasks." : " task.";
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                null,
                SUBJECT,
                "Currently in database you got: " + size + taskOrTasks
        ));
    }
}
