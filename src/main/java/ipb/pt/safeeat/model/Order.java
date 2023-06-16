package ipb.pt.safeeat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime time;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double subtotal;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double total;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer quantity;
    @NotEmpty(message = "invalid address")
    private Address address;
    @NotEmpty(message = "invalid payment")
    private Payment payment;
    @NotEmpty(message = "invalid delivery")
    private Delivery delivery;
    @NotEmpty(message = "invalid items")
    private List<Item> items;
    @DocumentReference
    @NotEmpty(message = "invalid restaurant")
    private Restaurant restaurant;
    @DocumentReference
    @NotEmpty(message = "invalid client")
    private User client;
    @DocumentReference
    private Feedback feedback;
}