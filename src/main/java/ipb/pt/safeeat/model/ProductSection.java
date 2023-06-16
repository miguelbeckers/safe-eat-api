package ipb.pt.safeeat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "productSections")
public class ProductSection {
    @Id
    private String id;
    @NotEmpty(message = "invalid name")
    private String name;
    @DocumentReference
    @NotEmpty(message = "invalid products")
    private List<Product> products;
}