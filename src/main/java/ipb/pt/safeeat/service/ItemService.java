package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ItemConstants;
import ipb.pt.safeeat.constants.ItemConstants;
import ipb.pt.safeeat.constants.ProductConstants;
import ipb.pt.safeeat.model.Item;
import ipb.pt.safeeat.model.Item;
import ipb.pt.safeeat.model.Product;
import ipb.pt.safeeat.repository.ItemRepository;
import ipb.pt.safeeat.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item findById(String id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ItemConstants.NOT_FOUND));
    }

    public Item create(Item item) {
        Product product = productRepository.findById(item.getProduct().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ProductConstants.NOT_FOUND));

        double subtotal = product.getPrice() * item.getQuantity();
        item.setSubtotal(subtotal);

        return itemRepository.save(item);
    }

    public Item update(Item item) {
        Item old = itemRepository.findById(item.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ItemConstants.NOT_FOUND));

        BeanUtils.copyProperties(item, old);
        return itemRepository.save(item);
    }

    public void delete(String id) {
        itemRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ItemConstants.NOT_FOUND));

        itemRepository.deleteById(id);
    }
}
