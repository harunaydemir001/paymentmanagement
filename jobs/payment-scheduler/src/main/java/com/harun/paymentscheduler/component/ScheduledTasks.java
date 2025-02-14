package com.harun.paymentscheduler.component;

import com.harun.paymentscheduler.service.PaymentInsertData;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final PaymentInsertData paymentInsertData;

    @Scheduled(cron = "0 0 * * * ?")
    public void execute() {
        paymentInsertData.insertData();
    }
}