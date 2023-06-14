package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.CartConstants;
import ipb.pt.safeeat.constants.RestaurantConstants;
import ipb.pt.safeeat.model.Cart;
import ipb.pt.safeeat.repository.CartRepository;
import ipb.pt.safeeat.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public Cart findById(UUID id) {
        return cartRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CartConstants.NOT_FOUND));
    }

    //TODO: insert product
    //TODO: remove product
    //TODO: reset
}
