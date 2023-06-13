package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "restaurants")
public class Restaurant {
    @Id
    private UUID id;
    @NotEmpty(message = "invalid name")
    private String name;
    @NotEmpty(message = "invalid logo")
    private String logo;
    @NotEmpty(message = "invalid cover")
    private String cover;
    @NotEmpty(message = "invalid delivery")
    private Delivery delivery;
    @DocumentReference
    private List<Product> products;
    @DocumentReference
    private List<ProductSection> productSections;
    @DocumentReference
    private List<Category> categories;
    @DocumentReference
    private List<Advertisement> advertisements;
}
