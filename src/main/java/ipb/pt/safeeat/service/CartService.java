package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
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
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.CART_NOT_FOUND));
    }

    public Cart create(Cart cart) {
        restaurantRepository.findById(cart.getRestaurant().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESTAURANT_NOT_FOUND));

        return cartRepository.save(cart);
    }

    public List<Cart> createMany(List<Cart> carts) {
        for (Cart cart : carts) {
            restaurantRepository.findById(cart.getRestaurant().getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.RESTAURANT_NOT_FOUND));
        }

        return cartRepository.saveAll(carts);
    }

    public Cart update(Cart cart) {
        Cart old = cartRepository.findById(cart.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.CART_NOT_FOUND));

        BeanUtils.copyProperties(cart, old);
        return cartRepository.save(cart);
    }

    public void delete(UUID id) {
        cartRepository.deleteById(id);
    }
}
