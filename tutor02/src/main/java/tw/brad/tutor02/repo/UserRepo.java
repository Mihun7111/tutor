package tw.brad.tutor02.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.brad.tutor02.entity.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
