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
@Document(collection = "restaurantSections")
public class RestaurantSection {
    @Id
    private String id;
    @NotEmpty(message = "Invalid name")
    private String name;
    @DocumentReference(lazy=true)
    @NotEmpty(message = "Invalid restaurants")
    private List<Restaurant> restaurants;
}
