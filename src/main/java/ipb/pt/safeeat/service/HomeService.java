package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Home;
import ipb.pt.safeeat.model.Ingredient;
import ipb.pt.safeeat.repository.HomeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class HomeService {
    @Autowired
    private HomeRepository homeRepository;

    public List<Home> getAll() {
        return homeRepository.findAll();
    }

    public Home findById(UUID id) {
        return homeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.HOME_NOT_FOUND));
    }

    public Home create(Home home) {
        return homeRepository.save(home);
    }

    public List<Home> createMany(List<Home> homes) {
        return homeRepository.saveAll(homes);
    }

    public Home update(Home home) {
        Home old = homeRepository.findById(home.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.HOME_NOT_FOUND));

        BeanUtils.copyProperties(home, old);
        return homeRepository.save(home);
    }

    public void delete(UUID id) {
        homeRepository.deleteById(id);
    }
}
