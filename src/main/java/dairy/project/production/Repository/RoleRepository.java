package dairy.project.production.Repository;


import dairy.project.production.Enumerated.RoleName;
import dairy.project.production.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}