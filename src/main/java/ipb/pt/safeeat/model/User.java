package ipb.pt.safeeat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @NotEmpty(message = "Invalid image")
    private String image;
    @NotEmpty(message = "Invalid name")
    private String name;
    @NotEmpty(message = "Invalid email")
    private String email;
    @NotEmpty(message = "Invalid cellphone")
    private String cellphone;
    @DocumentReference(lazy=true)
    private List<Restriction> restrictions;
    @JsonIgnore
    @DocumentReference(lazy=true)
    private Cart cart;
    @JsonIgnore
    @DocumentReference(lazy=true)
    private List<Payment> payments;
    @JsonIgnore
    @DocumentReference(lazy=true)
    private List<Address> address;
    @JsonIgnore
    @DocumentReference(lazy=true)
    private List<Order> orders;
    @JsonIgnore
    @DocumentReference(lazy=true)
    private List<Notification> notifications;
    @JsonIgnore
    @DocumentReference(lazy=true)
    private List<Restaurant> restaurants;
}