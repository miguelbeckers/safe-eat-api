package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.AddressConstants;
import ipb.pt.safeeat.model.Address;
import ipb.pt.safeeat.repository.AddressRepository;
import ipb.pt.safeeat.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address findById(String id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, AddressConstants.NOT_FOUND));
    }

    public Address create(Address address) {
        Address created = addressRepository.save(address);

        return created;
    }

    public Address update(Address address) {
        Address old = addressRepository.findById(address.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, AddressConstants.NOT_FOUND));

        BeanUtils.copyProperties(address, old);
        return addressRepository.save(address);
    }

    public void delete(String id) {
        addressRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, AddressConstants.NOT_FOUND));

        addressRepository.deleteById(id);
    }
}
