package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "deliveries")
public class Delivery {
    @Id
    private UUID id;
    @NotEmpty(message = "invalid name")
    private String name;
    @NotEmpty(message = "invalid price")
    private Double price;
    @NotEmpty(message = "invalid startTime")
    private LocalTime startTime;
    @NotEmpty(message = "invalid endTime")
    private LocalTime endTime;
}
