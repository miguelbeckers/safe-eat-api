package ipb.pt.safeeat.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "addresses")
public class Address {
    @Id
    private String id;
    @NotEmpty(message = "invalid name")
    private String name;
    @NotEmpty(message = "invalid isDefault")
    private Boolean isDefault;
    @NotEmpty(message = "invalid street")
    private String street;
    @NotEmpty(message = "invalid number")
    private String number;
    @NotEmpty(message = "invalid complement")
    private String complement;
    @NotEmpty(message = "invalid city")
    private String city;
    @NotEmpty(message = "invalid postalCode")
    private String postalCode;
}