package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Address;
import ipb.pt.safeeat.model.User;
import ipb.pt.safeeat.repository.AddressRepository;
import ipb.pt.safeeat.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address findById(UUID id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.ADDRESS_NOT_FOUND));
    }

    public Address create(Address address) {
        User user = userRepository.findById(address.getUser().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.USER_NOT_FOUND));

        Address created = addressRepository.save(address);

        user.getAddress().add(created);
        userRepository.save(user);
        return created;
    }

    public List<Address> createMany(List<Address> addresses) {
        for (Address address : addresses) {
            User user = userRepository.findById(address.getUser().getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.USER_NOT_FOUND));
            address.setUser(user);
        }

        List<Address> created = addressRepository.saveAll(addresses);

        for (Address address : created) {
            User user = address.getUser();
            user.getAddress().add(address);
            userRepository.save(user);
        }

        return created;
    }

    public Address update(Address address) {
        Address old = addressRepository.findById(address.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.ADDRESS_NOT_FOUND));

        BeanUtils.copyProperties(address, old);
        return addressRepository.save(address);
    }

    public void delete(UUID id) {
        addressRepository.deleteById(id);
    }
}
