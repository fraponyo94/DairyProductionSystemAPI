package dairy.project.production.Repository;

import dairy.project.production.entity.Milk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MilkRepository extends JpaRepository<Milk,Integer> {

    Optional<Milk> findByDateAndCow_CowTag(Date date, String CowTag);
}
