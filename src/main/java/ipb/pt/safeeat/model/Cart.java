package ipb.pt.safeeat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer quantity;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double subtotal;
    @DocumentReference
    private List<Item> items;
}