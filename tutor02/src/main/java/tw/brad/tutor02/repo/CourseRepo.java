package tw.brad.tutor02.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tw.brad.tutor02.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    // Specification 模式不需要額外定義方法
}
