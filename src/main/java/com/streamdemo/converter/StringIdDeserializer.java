//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.streamdemo.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.streamdemo.domain.generic.AbstractStringId;
import java.io.IOException;
import org.springframework.util.Assert;

public class StringIdDeserializer<T extends AbstractStringId> extends JsonDeserializer<T> {
  private StringIdConverter<T> converter;
  Class<T> serializedClass;

  public StringIdDeserializer(Class<T> serializedClass) {
    Assert.notNull(serializedClass, "serializedClass == null");
    this.converter = new StringIdConverter(serializedClass);
  }

  protected Class<T> getSerializedClass() {
    return this.serializedClass;
  }

  public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    return this.converter.convert(jp.getText());
  }
}
