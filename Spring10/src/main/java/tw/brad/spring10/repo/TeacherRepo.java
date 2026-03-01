package tw.brad.spring10.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tw.brad.spring10.entity.EnglishTeacher;

public interface TeacherRepo extends JpaRepository<EnglishTeacher, Integer>, JpaSpecificationExecutor<EnglishTeacher> {
}
