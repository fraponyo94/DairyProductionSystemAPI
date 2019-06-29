package dairy.project.production.Repository;

import dairy.project.production.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountReppository extends JpaRepository<AccountDetails,Integer> {
}
