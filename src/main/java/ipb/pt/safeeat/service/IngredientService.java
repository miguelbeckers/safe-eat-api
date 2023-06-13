package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.ExceptionConstants;
import ipb.pt.safeeat.model.Ingredient;
import ipb.pt.safeeat.model.Item;
import ipb.pt.safeeat.repository.IngredientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findById(UUID id) {
        return ingredientRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.INGREDIENT_NOT_FOUND));
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> createMany(List<Ingredient> ingredients) {
        return ingredientRepository.saveAll(ingredients);
    }

    public Ingredient update(Ingredient ingredient) {
        Ingredient old = ingredientRepository.findById(ingredient.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.INGREDIENT_NOT_FOUND));

        BeanUtils.copyProperties(ingredient, old);
        return ingredientRepository.save(ingredient);
    }

    public void delete(UUID id) {
        ingredientRepository.deleteById(id);
    }
}
