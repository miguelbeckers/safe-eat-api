package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.RestrictionConstants;
import ipb.pt.safeeat.constants.UserConstants;
import ipb.pt.safeeat.model.*;
import ipb.pt.safeeat.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RestrictionRepository restrictionRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));
    }

    public User create(User user) {
        if (user.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid password");
        }

        if (user.getAddress() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Addresses are not accepted. Use create address instead.");
        }

        if (user.getPayments() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Payments are not accepted. Use create payment instead.");
        }

        if (user.getOrders() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Orders are not accepted. Use create order instead.");
        }

        if (user.getRestaurants() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Restaurants are not accepted. Use create restaurant instead.");
        }

        if (user.getRestrictions() != null) {
            for (Restriction restriction : user.getRestrictions()) {
                restrictionRepository.findById(restriction.getId()).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestrictionConstants.NOT_FOUND));
            }
        }

        Cart cart = cartRepository.save(new Cart());
        user.setCart(cart);

        return userRepository.save(user);
    }

    public User update(User user) {
        User old = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));

        BeanUtils.copyProperties(user, old);
        return userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));

        userRepository.deleteById(id);
    }
}