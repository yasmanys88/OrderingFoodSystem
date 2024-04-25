package com.ordering.dto;

import com.ordering.documents.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String name;
    private String email;
    private String deliveryAddress;

}
