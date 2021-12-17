package com.streamdemo.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.streamdemo.domain.generic.AbstractStringId;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * General id serializer to text.
 */
public class StringIdSerializer<T extends AbstractStringId> extends JsonSerializer<T> {
  public StringIdSerializer(Class<T> serializedClass) {
    Assert.notNull(serializedClass, "serializedClass == null");
    this.serializedClass = serializedClass;
  }

  Class<T> serializedClass;
  protected Class<T> getSerializedClass() {
    return this.serializedClass;
  }


  @Override
  public void serialize(T value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
    String stringId = value.getValue();

    jgen.writeString(stringId);

  }
}