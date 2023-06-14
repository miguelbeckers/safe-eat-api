package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.UserConstants;
import ipb.pt.safeeat.model.*;
import ipb.pt.safeeat.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));
    }

    public User create(User user) {
//        Cart cart = new Cart(user, );
        return userRepository.save(user);
    }

    public User update(User user) {
        User old = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));

        BeanUtils.copyProperties(user, old);
        return userRepository.save(user);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
