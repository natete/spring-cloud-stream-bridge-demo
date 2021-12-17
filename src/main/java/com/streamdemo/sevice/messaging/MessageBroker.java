package com.streamdemo.sevice.messaging;


import com.streamdemo.domain.Event;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class MessageBroker {

  private final StreamBridge source;

  public MessageBroker(StreamBridge source) {
    this.source = source;
  }

  public void send(Event event) {
    source.send("demo-channel-out-0", event);
  }
}
