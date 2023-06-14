package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.*;
import ipb.pt.safeeat.model.Item;
import ipb.pt.safeeat.model.Order;
import ipb.pt.safeeat.model.Restaurant;
import ipb.pt.safeeat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order findById(UUID id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, OrderConstants.NOT_FOUND));
    }

    // TODO the dependencies should be embedded in an order and not being linked to the current objects
    public Order create(Order order) {
        addressRepository.findById(order.getAddress().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, AddressConstants.NOT_FOUND));

        paymentRepository.findById(order.getPayment().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, PaymentConstants.NOT_FOUND));

        deliveryRepository.findById(order.getDelivery().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, DeliveryConstants.NOT_FOUND));

        for (Item item : order.getItems()) {
            productRepository.findById(item.getProduct().getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ProductConstants.NOT_FOUND));
        }

        Restaurant restaurant = restaurantRepository.findById(order.getRestaurant().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));

        for (Item item : order.getItems()) {
            item.setSubtotal(item.getProduct().getPrice() * item.getQuantity());
        }

        order.setItems(itemRepository.saveAll(order.getItems()));
        order.setTime(LocalDateTime.now());

        Order created = orderRepository.save(order);
        restaurant.getOrders().add(created);
        restaurantRepository.save(restaurant);
        return created;
    }

    public Order update(Order order) {
        Order old = orderRepository.findById(order.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, OrderConstants.NOT_FOUND));

        if (!order.getStatus().equals(old.getStatus())) {
            old.setStatus(order.getStatus());
        }

        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only the status can be changed");
        }

        return orderRepository.save(order);
    }

    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }
}
