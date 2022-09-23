package com.emp.dto;

import com.emp.entity.Address;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private int addressId;
    @NotEmpty(message = "{address.address.empty}")
    @NotBlank(message = "{address.address.empty}")
    private String address;
    @NotEmpty(message = "{address.city.empty}")
    @NotBlank(message = "{address.city.empty}")
    private String city;
    @NotEmpty(message = "{address.state.empty}")
    @NotBlank(message = "{address.state.empty}")
    private String state;
    @NotEmpty(message = "{address.pincode.empty}")
    @NotBlank(message = "{address.pincode.empty}")
    @Pattern(regexp = "^[0-9]{6}$")
    private String pincode;
    private boolean isActive = true;
    private boolean isDeleted;

    public AddressDto(Address ad) {
        this.addressId = ad.getAddressId();
        this.address = ad.getAddress();
        this.city = ad.getCity();
        this.state = ad.getState();
        this.pincode = ad.getPincode();
        this.isActive = ad.isActive();
        this.isDeleted = ad.isDeleted();
    }
}
