package com.emp.entity;

import com.emp.dto.AddressDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private boolean isActive = true;
    private boolean isDeleted;

    public Address(AddressDto ad) {
        this.addressId = ad.getAddressId();
        this.address = ad.getAddress();
        this.city = ad.getCity();
        this.state = ad.getState();
        this.pincode = ad.getPincode();
        this.isActive = ad.isActive();
        this.isDeleted = ad.isDeleted();
    }
}
