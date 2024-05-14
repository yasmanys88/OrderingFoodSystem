package com.ordering.dto;

import com.ordering.utils.Order_Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoId {

    private String id;
    @NotBlank(message = "Order Number may not be blank")
    @Indexed(unique = true)
    private String orderNumber;
    @NotBlank(message = "User email may not be blank")
    private String userEmail;
    @NotBlank(message = "Restaurant  name may not be blank")
    private String restaurantName;
    @NotBlank(message = "Menu name may not be blank")
    private String menuName;
    @NotBlank(message = "Status may not be blank")
    private Order_Status status;
    @NotBlank(message = "Availability Status may not be blank")
    private Boolean availabilityStatus;
}
