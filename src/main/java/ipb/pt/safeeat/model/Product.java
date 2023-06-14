package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
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
@Document(collection = "products")
public class Product {
    @Id
    private UUID id;
    @NotEmpty(message = "invalid name")
    private String name;
    @NotEmpty(message = "invalid price")
    private Double price;
    @ReadOnlyProperty
    private Boolean isRestricted;
    @DocumentReference
    private List<Category> categories;
    @DocumentReference
    private List<Ingredient> ingredients;
    @NotEmpty(message = "invalid restaurant")
    @DocumentReference
    private Restaurant restaurant;
}