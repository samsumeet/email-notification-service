package com.app.mapp.service;


import com.app.mapp.domain.EmailNotificationEntity;
import com.app.mapp.domain.Response;
import org.springframework.stereotype.Service;

@Service
public interface IEmailNotifyService {

  Response sendEmail(final EmailNotificationEntity emailNotificationEntity);
}
