package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.RestrictionConstants;
import ipb.pt.safeeat.constants.UserConstants;
import ipb.pt.safeeat.model.*;
import ipb.pt.safeeat.repository.CartRepository;
import ipb.pt.safeeat.repository.RestrictionRepository;
import ipb.pt.safeeat.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private RestrictionRepository restrictionRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));
    }

    public User create(User user) {
        if(user.getPassword().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password");
        }

        Cart cart = cartRepository.save(new Cart());
        user.setCart(cart);
        return userRepository.save(user);
    }

    public User updateRestrictions(List<Restriction> restrictions, String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));

        List<Restriction> original = new ArrayList<>();

        for (Restriction restriction : restrictions) {
            Restriction foundRestriction = restrictionRepository.findById(restriction.getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestrictionConstants.NOT_FOUND));

            original.add(foundRestriction);
        }

        user.getRestrictions().addAll(original);
        return userRepository.save(user);
    }

    public User update(User user) {
        User old = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));

        BeanUtils.copyProperties(user, old);
        return userRepository.save(user);
    }

    public void delete(String id) {
        userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, UserConstants.NOT_FOUND));

        userRepository.deleteById(id);
    }
}