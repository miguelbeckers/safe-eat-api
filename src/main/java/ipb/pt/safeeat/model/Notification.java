package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "notifications")
public class Notification {
    @Id
    private UUID id;
    @NotEmpty(message = "invalid title")
    private String title;
    @ReadOnlyProperty
    private LocalDateTime time;
    @DocumentReference
    private Order order;
}
