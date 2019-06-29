package dairy.project.production.Repository;

import dairy.project.production.entity.Breeding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface BreedingRepository  extends JpaRepository<Breeding,Integer> {
    Optional<Breeding> findByDateAndCow_CowTag(Date date,String cowTag);
    Optional<Breeding> findByCow_CowTagAndStatus(String cowtag,boolean active);
}
