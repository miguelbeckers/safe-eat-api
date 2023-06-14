package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "advertisements")
public class Advertisement {
    @Id
    private String id;
    @NotEmpty(message = "invalid title")
    private String title;
    @NotEmpty(message = "invalid image")
    private String image;
    @NotEmpty(message = "invalid restaurant")
    @DocumentReference
    private Restaurant restaurant;
}