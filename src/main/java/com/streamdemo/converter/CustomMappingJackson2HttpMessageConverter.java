package com.streamdemo.converter;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.streamdemo.domain.generic.AbstractStringId;
import java.util.Iterator;
import java.util.Set;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CustomMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

  private static final String PACKAGE_TO_SCAN = "com.streamdemo";

  private static final Class<AbstractStringId> STRING_ID_CLASS = AbstractStringId.class;
  private boolean autoDetectProperties;

  public CustomMappingJackson2HttpMessageConverter(CustomMappingJackson2HttpMessageConverterConfiguration configuration) {
    this.autoDetectProperties = configuration.isAutoDetectProperties();
    this.customConfigure(this.getObjectMapper());
  }

  public void customConfigure(ObjectMapper om) {
    Assert.notNull(om, "ObjectMapper == null");
    om.configure(MapperFeature.AUTO_DETECT_FIELDS, this.autoDetectProperties);
    om.configure(MapperFeature.AUTO_DETECT_SETTERS, this.autoDetectProperties);
    om.configure(MapperFeature.AUTO_DETECT_GETTERS, this.autoDetectProperties);
    om.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, this.autoDetectProperties);
    om.configure(MapperFeature.AUTO_DETECT_FIELDS, this.autoDetectProperties);
    om.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
    om.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    this.autoconfigureAbstractStringId(om);
    this.addExtraSerializers(om);
  }

  private void addExtraSerializers(ObjectMapper om) {
    SimpleModule module = new SimpleModule("Other serializers");
    om.registerModule(module);
  }

  protected void autoconfigureAbstractStringId(ObjectMapper om) {
    SimpleModule customSerializersModule = new SimpleModule("Custom StringId Serializers");

    ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(new AssignableTypeFilter(STRING_ID_CLASS));

    Set<BeanDefinition> components = provider.findCandidateComponents(PACKAGE_TO_SCAN);
    for (BeanDefinition component : components) {
      try {
        Class<?> cls = Class.forName(component.getBeanClassName());
        customSerializersModule.addDeserializer(cls, new StringIdDeserializer(cls));
        customSerializersModule.addSerializer(cls, new StringIdSerializer(cls));
      }
      catch (ClassNotFoundException e) {
        throw new Error("Jackson error: unable to load a StringId class.", e);
      }
    }

    om.registerModule(customSerializersModule);
  }
}
