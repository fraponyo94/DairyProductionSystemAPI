package dairy.project.production.Repository;

import dairy.project.production.entity.Mortality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MortalityRepository extends JpaRepository<Mortality,Integer > {
    Optional<Mortality> findByCow_Dead(boolean dead);
    Optional<Mortality> findByCalf_Dead(boolean dead);

}
