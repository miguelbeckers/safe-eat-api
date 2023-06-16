package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "invalid name")
    private String name;
    @NotEmpty(message = "invalid logo")
    private String logo;
    @NotEmpty(message = "invalid cover")
    private String cover;
    @NotEmpty(message = "invalid owner")
    @DocumentReference
    private User owner;
    @DocumentReference
    private List<Delivery> deliveries;
    @DocumentReference
    private List<Product> products;
    @DocumentReference
    private List<ProductSection> productSections;
    @DocumentReference
    private List<Category> categories;
    @DocumentReference
    private List<Advertisement> advertisements;
    @DocumentReference
    private List<Order> orders;
}