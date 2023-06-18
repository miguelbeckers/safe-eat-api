package ipb.pt.safeeat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "restaurants")
public class Restaurant {
    @Id
    private String id;
    @NotEmpty(message = "Invalid name")
    private String name;
    @NotEmpty(message = "Invalid logo")
    private String logo;
    @NotEmpty(message = "Invalid cover")
    private String cover;
    @DocumentReference(lazy=true)
    @NotEmpty(message = "Invalid categories")
    private List<Category> categories;
    @DocumentReference(lazy=true)
    @JsonIgnore
    private List<Product> products;
    @DocumentReference(lazy=true)
    @JsonIgnore
    private List<ProductSection> productSections;
    @DocumentReference(lazy=true)
    @JsonIgnore
    private List<Advertisement> advertisements;
    @DocumentReference(lazy=true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Delivery> deliveries;
    @DocumentReference(lazy=true)
    @JsonIgnore
    private List<Order> orders;
    @DocumentReference(lazy=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Invalid owner")
    private User owner;
}