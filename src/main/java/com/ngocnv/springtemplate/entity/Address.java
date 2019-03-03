package com.ngocnv.springtemplate.entity;


import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address implements Serializable {

  private String state;
  private String district;
  private String street;

  public Address() {
  }

  public Address(String state, String district, String street) {
    this.state = state;
    this.district = district;
    this.street = street;
  }
}
