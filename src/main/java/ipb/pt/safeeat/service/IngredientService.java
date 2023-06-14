package ipb.pt.safeeat.service;

import ipb.pt.safeeat.constants.IngredientConstants;
import ipb.pt.safeeat.constants.RestaurantConstants;
import ipb.pt.safeeat.model.Ingredient;
import ipb.pt.safeeat.model.Restriction;
import ipb.pt.safeeat.repository.IngredientRepository;
import ipb.pt.safeeat.repository.RestrictionRepository;
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

    @Autowired
    private RestrictionRepository restrictionRepository;

    //TODO change the isRestrict property according to the current user
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient findById(UUID id) {
        return ingredientRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, IngredientConstants.NOT_FOUND));
    }

    public Ingredient create(Ingredient ingredient) {
        checkIngredients(ingredient);

        return ingredientRepository.save(ingredient);
    }

    private void checkIngredients(Ingredient ingredient) {
        for(Restriction restriction : ingredient.getRestrictions()) {
            restrictionRepository.findById(restriction.getId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, RestaurantConstants.NOT_FOUND));
        }
    }

    public Ingredient update(Ingredient ingredient) {
        Ingredient old = ingredientRepository.findById(ingredient.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, IngredientConstants.NOT_FOUND));

        checkIngredients(ingredient);
        BeanUtils.copyProperties(ingredient, old);
        return ingredientRepository.save(ingredient);
    }

    public void delete(UUID id) {
        ingredientRepository.deleteById(id);
    }
}
