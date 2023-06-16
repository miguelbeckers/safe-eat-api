package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@Document(collection = "deliveries")
public class Delivery {
    @Id
    private String id;
    @NotEmpty(message = "Invalid name")
    private String name;
    @NotEmpty(message = "Invalid price")
    private Double price;
    @NotEmpty(message = "Invalid startTime")
    private LocalTime startTime;
    @NotEmpty(message = "Invalid endTime")
    private LocalTime endTime;
}
