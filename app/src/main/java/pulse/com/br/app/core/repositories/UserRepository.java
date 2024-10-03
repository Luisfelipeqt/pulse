package pulse.com.br.app.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pulse.com.br.app.core.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}