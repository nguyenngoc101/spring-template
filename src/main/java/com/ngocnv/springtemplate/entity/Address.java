package com.ngocnv.springtemplate.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

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
