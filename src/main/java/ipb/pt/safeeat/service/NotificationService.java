package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Notification;
import ipb.pt.safeeat.model.Order;
import ipb.pt.safeeat.repository.NotificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    public Notification findById(UUID id) {
        return notificationRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.NOTIFICATION_NOT_FOUND));
    }

    public Notification create(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> createMany(List<Notification> notifications) {
        return notificationRepository.saveAll(notifications);
    }

    public Notification update(Notification notification) {
        Notification old = notificationRepository.findById(notification.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.NOTIFICATION_NOT_FOUND));

        BeanUtils.copyProperties(notification, old);
        return notificationRepository.save(notification);
    }

    public void delete(UUID id) {
        notificationRepository.deleteById(id);
    }
}
