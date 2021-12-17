package com.streamdemo.domain;

import lombok.Value;

@Value
public class Event {

  String eventType;

  EventId id;

}
