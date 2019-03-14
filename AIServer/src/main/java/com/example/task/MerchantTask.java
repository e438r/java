package com.example.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MerchantTask {
    Logger logger = LoggerFactory.getLogger(MerchantTask.class);


//    @Scheduled(cron = "0 0 9 * * ?")
    public void addMercchant(){

    }
}
