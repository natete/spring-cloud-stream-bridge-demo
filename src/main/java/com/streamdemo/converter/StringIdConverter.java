package com.streamdemo.converter;

import com.streamdemo.domain.generic.AbstractStringId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Generic converter from a long in string to format to a subclass of AbstractStringId.
 */
public class StringIdConverter<T extends AbstractStringId> implements Converter<String, T> {
  public final static String FINAL_STATIC_METHOD = "valueOf";

  public StringIdConverter(Class<T> serializedClass) {
    Assert.notNull(serializedClass, "serializedClass == null");
    this.serializedClass = serializedClass;
  }

  Class<T> serializedClass;
  protected Class<T> getSerializedClass() {
    return this.serializedClass;
  }


  @Override
  public T convert(String stringId) {
    if (stringId == null || stringId.isEmpty()) {
      return null;
    }

    Class<T> c = getSerializedClass();

    try {
      Method m = c.getMethod(FINAL_STATIC_METHOD, String.class);
      return (T) m.invoke(null, stringId);
    } catch (SecurityException | NoSuchMethodException | IllegalArgumentException | IllegalAccessException |
        InvocationTargetException e) {

      throw new IllegalStateException("Programmer error. String couldn't be deserialize into class: " + c, e);
    }
  }
}
