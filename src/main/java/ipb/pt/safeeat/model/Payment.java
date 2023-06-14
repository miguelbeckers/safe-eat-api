package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.YearMonth;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;
    @NotEmpty(message = "invalid type")
    private String type;
    @NotEmpty(message = "invalid name")
    private String name;
    @NotEmpty(message = "invalid number")
    private Integer number;
    @NotEmpty(message = "invalid expirationDate")
    private YearMonth expirationDate;
    @NotEmpty(message = "invalid ccv")
    private String cvv;
}
