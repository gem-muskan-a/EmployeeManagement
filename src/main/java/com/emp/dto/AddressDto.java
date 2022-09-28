package com.emp.dto;

import com.emp.entity.Address;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private int addressId;
    @NotNull(message = "{address.address.empty}")
    @Size(min =3,max=25, message = "{address.address.size}")
    private String address;
    @NotNull(message = "{address.city.empty}")
    @Size(min =3,max=25, message = "{address.city.size}")
    private String city;
    @NotNull(message = "{address.state.empty}")
    @Size(min =3,max=25, message = "{address.state.size}")
    private String state;
    @NotNull(message = "{address.pincode.empty}")
    @Pattern(regexp = "^[0-9]{6}$")
    private String pincode;
    private boolean isActive = true;
    private boolean isDeleted = false;


}
