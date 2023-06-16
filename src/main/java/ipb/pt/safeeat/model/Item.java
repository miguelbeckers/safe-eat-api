package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@NoArgsConstructor
@Document(collection = "items")
public class Item {
    @Id
    private String id;
    @DocumentReference
    private Product product;
    @NotEmpty(message = "invalid quantity")
    private Integer quantity;
    @ReadOnlyProperty
    private Double subtotal;
}