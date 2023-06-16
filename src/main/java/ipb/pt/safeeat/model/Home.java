package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "homes")
public class Home {
    @Id
    private String id;
    @DocumentReference
    @NotNull(message = "Invalid content")
    private List<Object> content;
}