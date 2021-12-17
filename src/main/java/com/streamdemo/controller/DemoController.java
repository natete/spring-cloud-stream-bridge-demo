package com.streamdemo.controller;

import com.streamdemo.domain.Event;
import com.streamdemo.domain.EventId;
import com.streamdemo.sevice.messaging.MessageBroker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class DemoController {

  private final MessageBroker broker;

  public DemoController(MessageBroker broker) {
    this.broker = broker;
  }

  @GetMapping("/send")
  public void send() {
    broker.send(new Event("TENANT_UPDATE", EventId.valueOf("6db30e60-3e4d-47b4-9e8a-f1f7cefa3b8a")));
  }
}
