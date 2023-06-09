package fr.prody.prody.repositories;

import fr.prody.prody.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Category findByName(String name);

}
