package com.ordering.dto;

import com.ordering.documents.Menu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    @NotBlank(message = "Name of Restaurant may not be blank")
    @Indexed(unique = true)
    private String name;

    @NotBlank(message = "Address of Restaurant may not be blank")
    private String address;

    @NotBlank(message = "Contact Information of Restaurant may not be blank")
    private String contact_info;

    @NotNull(message = "Menu in the restaurant cannot be null")
    private MenuDto menu;
}
