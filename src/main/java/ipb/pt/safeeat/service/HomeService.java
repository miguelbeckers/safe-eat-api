package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.AdvertisementConstants;
import ipb.pt.safeeat.constants.HomeConstants;
import ipb.pt.safeeat.constants.RestaurantSectionConstants;
import ipb.pt.safeeat.model.Advertisement;
import ipb.pt.safeeat.model.Home;
import ipb.pt.safeeat.model.RestaurantSection;
import ipb.pt.safeeat.repository.AdvertisementRepository;
import ipb.pt.safeeat.repository.HomeRepository;
import ipb.pt.safeeat.repository.RestaurantSectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class HomeService {
    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private RestaurantSectionRepository restaurantSectionRepository;
    @Autowired
    private AdvertisementRepository advertisementRepository;

    public List<Home> getAll() {
        return homeRepository.findAll();
    }

    public Home findById(String id) {
        return homeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, HomeConstants.NOT_FOUND));
    }

    public Home create() {
        List<Object> content = new ArrayList<>();

        List<RestaurantSection> restaurantSections = restaurantSectionRepository.findAll();
        List<Advertisement> advertisements = advertisementRepository.findAll();

        content.addAll(restaurantSections);
        content.addAll(advertisements);

        Collections.shuffle(content, new Random(System.nanoTime()));

        Home home = new Home();
        home.setContent(content);

        return homeRepository.save(home);
    }

    public Home update(Home home) {
        Home old = homeRepository.findById(home.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, HomeConstants.NOT_FOUND));

        BeanUtils.copyProperties(home, old);
        return homeRepository.save(home);
    }

    public void delete(String id) {
        homeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, HomeConstants.NOT_FOUND));

        homeRepository.deleteById(id);
    }
}
