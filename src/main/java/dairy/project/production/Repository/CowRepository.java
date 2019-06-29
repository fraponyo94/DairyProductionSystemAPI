package dairy.project.production.Repository;

import dairy.project.production.entity.Cow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CowRepository extends JpaRepository<Cow,String > {
   List<Cow> findByDead(boolean dead);

}
