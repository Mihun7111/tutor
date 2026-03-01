package tw.brad.tutor.repp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tw.brad.tutor.entity.EnglishTeacher;


public interface TeacherRepo extends JpaRepository<EnglishTeacher, Integer>, JpaSpecificationExecutor<EnglishTeacher> {
}
