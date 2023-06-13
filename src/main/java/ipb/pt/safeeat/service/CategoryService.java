package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Cart;
import ipb.pt.safeeat.model.Category;
import ipb.pt.safeeat.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.CATEGORY_NOT_FOUND));
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> createMany(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    public Category update(Category category) {
        Category old = categoryRepository.findById(category.getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.CATEGORY_NOT_FOUND));

        BeanUtils.copyProperties(category, old);
        return categoryRepository.save(category);
    }

    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }
}
