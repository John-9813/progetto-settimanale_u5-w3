package johnoliveira.progetto_settimanale_u5_w3.repositories;

import johnoliveira.progetto_settimanale_u5_w3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepos extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}

