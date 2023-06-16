package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.CartConstants;
import ipb.pt.safeeat.model.Cart;
import ipb.pt.safeeat.model.Item;
import ipb.pt.safeeat.repository.CartRepository;
import ipb.pt.safeeat.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public Cart findById(String id) {
        return cartRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CartConstants.NOT_FOUND));
    }

    public Cart create(Cart cart) {
        if(cart.getItems() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Items are not accepted. Use create items instead.");
        }

        int quantity = cart.getItems().size();
        double subtotal = cart.getItems().stream().mapToDouble(Item::getSubtotal).sum();

        cart.setQuantity(quantity);
        cart.setSubtotal(subtotal);

        return cartRepository.save(cart);
    }

    public Cart update(Cart cart) {
        Cart old = cartRepository.findById(cart.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CartConstants.NOT_FOUND));

        BeanUtils.copyProperties(cart, old);
        return cartRepository.save(cart);
    }

    public void delete(String id) {
        cartRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, CartConstants.NOT_FOUND));

        cartRepository.deleteById(id);
    }
}
