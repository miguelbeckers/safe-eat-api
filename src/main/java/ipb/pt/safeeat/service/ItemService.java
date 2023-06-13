package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Item;
import ipb.pt.safeeat.model.Notification;
import ipb.pt.safeeat.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
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

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item findById(UUID id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.ITEM_NOT_FOUND));
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> createMany(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    public Item update(Item item) {
        Item old = itemRepository.findById(item.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.ITEM_NOT_FOUND));

        BeanUtils.copyProperties(item, old);
        return itemRepository.save(item);
    }

    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }
}
