package tw.brad.tutor01.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.brad.tutor01.entity.Tutor;

@Repository
public interface TutorRepo extends JpaRepository<Tutor, Long> {

}
