package com.app.mapp.service.impl;

import com.app.mapp.domain.EmailNotificationEntity;
import com.app.mapp.domain.Response;
import com.app.mapp.exception.EmailSendException;
import com.app.mapp.service.IEmailNotifyService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmailNotifyService implements IEmailNotifyService {

  @Autowired
  private RestTemplate restTemplate;
  private JavaMailSender javaMailSender;

  public EmailNotifyService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Override
  public Response sendEmail(EmailNotificationEntity emailNotificationEntity) {
    boolean emailFutureResponse;

    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      byte[] attachmentBytes = restTemplate
          .getForObject(emailNotificationEntity.getURI(), byte[].class);

      UrlResource urlResource = new UrlResource(emailNotificationEntity.getURI());
      Path tempFile = createTempFile(urlResource, attachmentBytes);

      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setTo(emailNotificationEntity.getEmailIds());
      helper.setSubject(emailNotificationEntity.getSubject());
      helper.setText(emailNotificationEntity.getBody());
      helper.addAttachment(urlResource.getFilename(), tempFile.toFile());

      emailFutureResponse = send(message).get();

      Files.delete(tempFile);
    } catch (Exception exception) {
      throw new EmailSendException(exception.getMessage());
    }
    return new Response(emailFutureResponse, "Successfully Sent mail");
  }

  private Path createTempFile(UrlResource urlResource, byte[] attachmentBytes) throws IOException {
    return Files.write(Paths.get(urlResource.getFilename()), attachmentBytes);
  }

  @Async
  public Future<Boolean> send(MimeMessage message) {
    javaMailSender.send(message);
    return new AsyncResult<Boolean>(true);
  }
}
