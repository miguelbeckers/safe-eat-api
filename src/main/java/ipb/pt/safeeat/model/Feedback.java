package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@NoArgsConstructor
@Document(collection = "feedbacks")
public class Feedback {
    @Id
    private String id;
    @NotEmpty(message = "Invalid rating")
    private Integer rating;
    @NotEmpty(message = "Invalid comment")
    private String comment;
    @NotEmpty(message = "Invalid order")
    @DocumentReference
    private Order order;
}
