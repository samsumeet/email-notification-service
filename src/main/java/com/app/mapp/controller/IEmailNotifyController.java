package com.app.mapp.controller;

import com.app.mapp.domain.EmailNotificationEntity;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public interface IEmailNotifyController {

  @ApiOperation(value = "Send email notification to users")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 500, message = "Failure")})
  @PostMapping("sendmail")
  ResponseEntity sendEmail(final @RequestBody EmailNotificationEntity emailNotificationEntity);
}
