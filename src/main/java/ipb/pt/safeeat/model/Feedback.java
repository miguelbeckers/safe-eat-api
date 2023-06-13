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
@Document(collection = "feedbacks")
public class Feedback {
    @Id
    private UUID id;
    @NotEmpty(message = "invalid rating")
    private Integer rating;
    @NotEmpty(message = "invalid comment")
    private String comment;

    @NotEmpty(message = "invalid order")
    @DocumentReference
    private Order order;
}
