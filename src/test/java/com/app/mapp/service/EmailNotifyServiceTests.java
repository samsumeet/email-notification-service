package com.app.mapp.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import com.app.mapp.WebMockTest;
import com.app.mapp.domain.EmailNotificationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class EmailNotifyServiceTests {

  @Autowired
  @MockBean
  private IEmailNotifyService emailNotifyService;

  @Test
  public void test_Email_success() {
    EmailNotificationEntity emailNotificationEntity = new EmailNotificationEntity();
    emailNotificationEntity.setBody("Test");
    emailNotificationEntity.setEmailIds(new String[]{"test"});
    emailNotificationEntity.setSubject("Test");
    emailNotificationEntity.setURI("http://localhost/demo.jpg");

    when(emailNotifyService.sendEmail(emailNotificationEntity))
        .thenReturn(WebMockTest.getMockedResponse());

    assertEquals(emailNotifyService.sendEmail(emailNotificationEntity).isSuccess(),
        WebMockTest.getMockedResponse().isSuccess());
  }
}
