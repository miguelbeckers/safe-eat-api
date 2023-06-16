package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.*;
import ipb.pt.safeeat.model.Item;
import ipb.pt.safeeat.model.Order;
import ipb.pt.safeeat.model.Restaurant;
import ipb.pt.safeeat.model.User;
import ipb.pt.safeeat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order findById(String id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, OrderConstants.NOT_FOUND));
    }

    public Order create(Order order) {
        if(order.getFeedback() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Feedbacks are not accepted. User create feedback endpoint instead.");
        }

        addressRepository.findById(order.getAddress().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, AddressConstants.NOT_FOUND));

        paymentRepository.findById(order.getPayment().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, PaymentConstants.NOT_FOUND));

        deliveryRepository.findById(order.getDelivery().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, DeliveryConstants.NOT_FOUND));

        for(Item item : order.getItems()) {
            productRepository.findById(item.getProduct().getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ProductConstants.NOT_FOUND));
        }

        Restaurant restaurant = restaurantRepository.findById(order.getRestaurant().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));

        User client = userRepository.findById(order.getClient().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));

        double subtotal = order.getItems().stream().mapToDouble(Item::getSubtotal).sum();
        double total = subtotal + order.getDelivery().getPrice();

        order.setStatus("Registered");
        order.setTime(LocalDateTime.now());
        order.setSubtotal(subtotal);
        order.setTotal(total);

        Order created = orderRepository.save(order);

        restaurant.getOrders().add(created);
        restaurantRepository.save(restaurant);

        client.getOrders().add(created);
        userRepository.save(client);

        return created;
    }

    public Order update(Order order) {
        Order old = orderRepository.findById(order.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, OrderConstants.NOT_FOUND));

        if (!order.getStatus().equals(old.getStatus())) {
            old.setStatus(order.getStatus());
        }

        return orderRepository.save(order);
    }

    public void delete(String id) {
        orderRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, OrderConstants.NOT_FOUND));
                
        orderRepository.deleteById(id);
    }
}
