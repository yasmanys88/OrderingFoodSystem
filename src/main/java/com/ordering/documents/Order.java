package com.ordering.documents;

import com.ordering.utils.Order_Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    private String id;
    private String user_email;
    private String restaurant_name;
    private String menu_name;
    private Order_Status status;
    private Boolean availability_Status;
}
