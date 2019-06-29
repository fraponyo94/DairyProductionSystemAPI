package dairy.project.production.Repository;

import dairy.project.production.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreedRepository  extends JpaRepository<Breed,Long> {

    Optional<Breed> findByName(String name);
}
