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
@Document(collection = "restaurantSections")
public class RestaurantSection {
    @Id
    private String id;
    @NotEmpty(message = "invalid name")
    private String name;
    @DocumentReference
    private List<Restaurant> restaurants;
}
