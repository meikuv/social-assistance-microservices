package meikuv.xyz.authservice.repository;

import meikuv.xyz.authservice.dto.UserDTO;
import meikuv.xyz.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    @Query(value = "SELECT * FROM s_users", nativeQuery = true)
    List<UserDTO> getAllUsers();

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
