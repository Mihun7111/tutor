package tw.brad.tutor03.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.brad.tutor03.entity.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
