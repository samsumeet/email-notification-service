package com.app.mapp.domain;

import lombok.Data;

@Data
public class EmailNotificationEntity {

  private String URI;
  private String subject;
  private String body;
  private String[] emailIds;
}
