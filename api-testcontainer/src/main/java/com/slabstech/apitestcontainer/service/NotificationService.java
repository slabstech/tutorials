package com.slabstech.apitestcontainer.service;

import com.slabstech.apitestcontainer.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    public void notifySomeoneAboutChange(User user) {
        log.info("Notification about user: {}", user);
    }

}
