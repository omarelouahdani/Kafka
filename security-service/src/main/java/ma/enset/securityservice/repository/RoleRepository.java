package ma.enset.securityservice.repository;

import ma.enset.securityservice.entities.Role;
import ma.enset.securityservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
