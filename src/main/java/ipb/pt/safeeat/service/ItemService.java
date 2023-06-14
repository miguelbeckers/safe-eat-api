package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ItemConstants;
import ipb.pt.safeeat.model.Item;
import ipb.pt.safeeat.repository.ItemRepository;
import ipb.pt.safeeat.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item findById(UUID id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ItemConstants.NOT_FOUND));
    }
}
