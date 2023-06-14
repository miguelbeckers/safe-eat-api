package ipb.pt.safeeat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.UUID;

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
    @DocumentReference(lookup="{'restrictions':?#{#self._id} }")
    @ReadOnlyProperty
    private List<Restriction> restrictions;
    @DocumentReference
    @ReadOnlyProperty
    private List<Address> address;
    @DocumentReference
    @ReadOnlyProperty
    private List<Payment> payments;
    @DocumentReference
    @ReadOnlyProperty
    private List<Order> orders;
    @DocumentReference
    @ReadOnlyProperty
    private Cart cart;
}