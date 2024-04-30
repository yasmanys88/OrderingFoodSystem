package com.ordering.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

    @NotBlank(message = "Name of Menu may not be blank")
    private String name;

    @NotBlank(message = "Description may not be blank")
    private String description;

    @NotNull(message = "Price may not be null")
    private Double price;

    @NotNull(message = "Availability Status may not be Null")
    private Boolean availability_Status;
}
