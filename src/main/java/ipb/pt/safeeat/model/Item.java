package ipb.pt.safeeat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @NotEmpty(message = "invalid product")
    private Product product;
    @NotEmpty(message = "invalid quantity")
    private Integer quantity;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double subtotal;
}