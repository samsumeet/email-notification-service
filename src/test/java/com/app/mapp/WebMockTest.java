package com.app.mapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.app.mapp.controller.IEmailNotifyController;
import com.app.mapp.domain.EmailNotificationEntity;
import com.app.mapp.domain.Response;
import com.app.mapp.service.IEmailNotifyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(IEmailNotifyController.class)
public class WebMockTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IEmailNotifyService service;

  public static Response getMockedResponse() {

    Response response = new Response(true, "Successfully Sent mail");
    return response;
  }

  @Test
  public void emailNotificationReturnResponseFromService() throws Exception {
    EmailNotificationEntity emailNotificationEntity = new EmailNotificationEntity();
    emailNotificationEntity.setBody("Test");
    emailNotificationEntity.setEmailIds(new String[]{"test"});
    emailNotificationEntity.setSubject("Test");
    emailNotificationEntity.setURI("localhost");

    when(service.sendEmail(emailNotificationEntity))
        .thenReturn(getMockedResponse());

    this.mockMvc.perform(post("/sendmail")
        .content(asJsonString(emailNotificationEntity))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(
            "{\"success\":true,\"message\":\"Successfully Sent mail\"}"));
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}