package com.app.mapp;

import static org.assertj.core.api.Assertions.assertThat;

import com.app.mapp.controller.IEmailNotifyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailNotificationApplicationTests {

  @Autowired
  private IEmailNotifyController controller;

  @Test
  void contextLoads() {
    assertThat(controller).isNotNull();
  }

}
