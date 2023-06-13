package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Order;
import ipb.pt.safeeat.model.Payment;
import ipb.pt.safeeat.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order findById(UUID id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.ORDER_NOT_FOUND));
    }

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> createMany(List<Order> orders) {
        return orderRepository.saveAll(orders);
    }

    public Order update(Order order) {
        Order old = orderRepository.findById(order.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.ORDER_NOT_FOUND));

        BeanUtils.copyProperties(order, old);
        return orderRepository.save(order);
    }

    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }
}
