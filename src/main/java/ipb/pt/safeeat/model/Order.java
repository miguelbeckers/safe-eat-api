package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    @NotEmpty(message = "invalid status")
    private String status;
    @ReadOnlyProperty
    private LocalDateTime time;
    @ReadOnlyProperty
    private Double subtotal;
    @ReadOnlyProperty
    private Double total;
    @ReadOnlyProperty
    private Integer quantity;
    @NotEmpty(message = "invalid address")
    private Address address;
    @NotEmpty(message = "invalid payment")
    private Payment payment;
    @NotEmpty(message = "invalid items")
    private List<Item> items;
    @NotEmpty(message = "invalid delivery")
    private Delivery delivery;
    @DocumentReference
    @NotEmpty(message = "invalid restaurant")
    private Restaurant restaurant;
    @DocumentReference
    private Feedback feedback;
}