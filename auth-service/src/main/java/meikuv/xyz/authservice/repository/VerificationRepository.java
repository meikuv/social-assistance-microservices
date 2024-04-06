package meikuv.xyz.authservice.repository;

import meikuv.xyz.authservice.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Optional<Verification> findByCodeAndEmail(String code, String email);
}
