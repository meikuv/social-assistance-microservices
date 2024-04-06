package meikuv.xyz.authservice.repository;

import meikuv.xyz.authservice.enums.ERole;
import meikuv.xyz.authservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
