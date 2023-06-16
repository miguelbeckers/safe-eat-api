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

    public Order findById(String id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, OrderConstants.NOT_FOUND));
    }

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

//        private String status;
//        private LocalDateTime time;
//        private Double subtotal;
//        private Double total;
//        private Integer quantity;
//        private Address address;
//        private Payment payment;
//        private List<Item> items;
//        private Delivery delivery;
//        private Restaurant restaurant;
//        private Feedback feedback;
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
