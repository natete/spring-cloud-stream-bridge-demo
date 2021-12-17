//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.streamdemo.domain.generic;

import org.springframework.util.Assert;

public abstract class AbstractStringId implements Id, Comparable {
  private String id;

  protected AbstractStringId( String id) {
    Assert.isTrue(!id.isEmpty(), "id must be a not empty string");
    this.id = id;
  }

  final public String getValue() {
    return id;
  }

  final public String toString() {
    return getValue();
  }

  public boolean equals(Object o) {
    if (o == null)
      return false;

    if (!getClass().equals(o.getClass()))
      return false;

    return getValue().equals(((AbstractStringId)o).getValue());
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public int compareTo(Object o) {
    if (o == null || !o.getClass().equals(getClass())) {
      return -1;
    }

    return getValue().compareTo(((AbstractStringId) o).getValue());
  }
}
