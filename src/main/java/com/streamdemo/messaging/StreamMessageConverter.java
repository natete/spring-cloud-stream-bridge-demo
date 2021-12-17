//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.streamdemo.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

public class StreamMessageConverter extends AbstractMessageConverter {

  private static final Logger log = LoggerFactory.getLogger(StreamMessageConverter.class);
  private static final MimeType JSON_MIME_TYPE = MimeType.valueOf("application/json");
  private final Class<?> clazz;
  private final ObjectMapper objectMapper;

  public StreamMessageConverter(ObjectMapper objectMapper) {
    this(objectMapper, Object.class);
  }

  public StreamMessageConverter(ObjectMapper objectMapper, Class<?> clazz) {
    super(JSON_MIME_TYPE);
    this.objectMapper = objectMapper;
    this.clazz = clazz;
  }

  protected boolean supports(Class<?> clazz) {
    return clazz.equals(clazz);
  }

  @Nullable
  protected Object convertFromInternal(Message<?> message, Class<?> targetClass, @Nullable Object conversionHint) {
    Object payload = message.getPayload();

    try {
      return payload instanceof byte[] ? this.objectMapper.readValue((byte[]) payload, targetClass)
          : this.objectMapper.readValue((String) payload, targetClass);
    } catch (IOException var6) {
      log.info("Unable to read json payload as object", var6);
      return null;
    }
  }

  @Nullable
  protected Object convertToInternal(Object payload, @Nullable MessageHeaders headers, @Nullable Object conversionHint) {
    try {
      return this.objectMapper.writeValueAsString(payload).getBytes("UTF-8");
    } catch (IOException var5) {
      log.info("Unable to write payload as json", var5);
      return null;
    }
  }
}
