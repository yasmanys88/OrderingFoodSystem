package com.ordering.documents;

import com.ordering.utils.Order_Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    private String id;

    @NotBlank(message = "Order Number may not be blank")
    @Indexed(unique = true)
    private Integer order_number;
    @NotBlank(message = "User email may not be blank")
    private String user_email;
    @NotBlank(message = "Restaurant  name may not be blank")
    private String restaurant_name;
    @NotBlank(message = "Menu name may not be blank")
    private String menu_name;
    @NotBlank(message = "Status may not be blank")
    private Order_Status status;
    @NotBlank(message = "Availability Status may not be blank")
    private Boolean availability_Status;
}
