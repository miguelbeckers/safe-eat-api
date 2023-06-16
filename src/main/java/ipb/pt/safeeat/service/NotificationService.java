package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.NotificationConstants;
import ipb.pt.safeeat.constants.OrderConstants;
import ipb.pt.safeeat.model.Notification;
import ipb.pt.safeeat.repository.NotificationRepository;
import ipb.pt.safeeat.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    public Notification findById(String id) {
        return notificationRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, NotificationConstants.NOT_FOUND));
    }

    public Notification create(Notification notification) {
        orderRepository.findById(notification.getOrder().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, OrderConstants.NOT_FOUND));

        return notificationRepository.save(notification);

//        private String title;
//        private LocalDateTime time;
//        private Order order;
    }

    public Notification update(Notification notification) {
        Notification old = notificationRepository.findById(notification.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, NotificationConstants.NOT_FOUND));

        if(!notification.getOrder().equals(old.getOrder())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, OrderConstants.CHANGED);
        }

        BeanUtils.copyProperties(notification, old);
        return notificationRepository.save(notification);
    }

    public void delete(String id) {
        notificationRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, NotificationConstants.NOT_FOUND));
                
        notificationRepository.deleteById(id);
    }
}
