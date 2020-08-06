package com.app.mapp.controller.impl;

import com.app.mapp.controller.IEmailNotifyController;
import com.app.mapp.domain.EmailNotificationEntity;
import com.app.mapp.service.IEmailNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EmailNotifyController implements IEmailNotifyController {

  @Autowired
  private IEmailNotifyService emailNotifyService;

  @Override
  public ResponseEntity sendEmail(EmailNotificationEntity emailNotificationEntity) {
    return ResponseEntity.ok(emailNotifyService.sendEmail(emailNotificationEntity));
  }
}
