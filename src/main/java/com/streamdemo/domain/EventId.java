package com.streamdemo.domain;

import com.streamdemo.domain.generic.AbstractStringId;

public class EventId extends AbstractStringId {

  protected EventId(String id) {
    super(id);
  }

  public static EventId valueOf(String id) {
    return new EventId(id);
  }
}
