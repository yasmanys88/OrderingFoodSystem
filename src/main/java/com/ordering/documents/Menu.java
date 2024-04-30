package com.ordering.documents;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Menu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Menu {
    @Id
    private String id;

    @NotBlank(message = "Name of Menu may not be blank")
    @Indexed(unique = true)
    private String name;

    @NotBlank(message = "Description may not be blank")
    private String description;

    @NotBlank(message = "Price may not be blank")
    @Min(value = 0, message = "The price cannot be negative")
    private Double price;

    @NotNull(message = "Availability Status may not be Null")
    private Boolean availability_Status;
}
