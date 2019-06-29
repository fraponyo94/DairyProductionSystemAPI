package dairy.project.production.Repository;

import dairy.project.production.entity.Calving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalvingRepository extends JpaRepository<Calving,Integer> {

    Optional<Calving> findByCalfId(String calfId);
    List<Calving> findByCow_CowTag(String cowTag);
    List<Calving> findByDead(boolean dead);


}
