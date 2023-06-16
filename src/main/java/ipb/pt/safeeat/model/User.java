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
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotEmpty(message = "invalid image")
    private String image;
    @NotEmpty(message = "invalid name")
    private String name;
    @NotEmpty(message = "invalid email")
    private String email;
    @NotEmpty(message = "invalid cellphone")
    private String cellphone;
    @DocumentReference
    private List<Restriction> restrictions;
    @DocumentReference
    private List<Address> address;
    @DocumentReference
    private List<Payment> payments;
    @DocumentReference
    private List<Order> orders;
    @DocumentReference
    private Cart cart;
    @DocumentReference
    private List<Restaurant> restaurants;
}