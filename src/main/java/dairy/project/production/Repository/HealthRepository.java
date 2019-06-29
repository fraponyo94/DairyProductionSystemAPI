package dairy.project.production.Repository;

import dairy.project.production.entity.Health;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRepository extends JpaRepository<Health,Integer> {
}
