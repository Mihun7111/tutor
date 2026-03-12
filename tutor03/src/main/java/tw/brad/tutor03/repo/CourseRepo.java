package tw.brad.tutor03.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tw.brad.tutor03.entity.Course;
import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> , JpaSpecificationExecutor<Course>{

    /**
     * 透過老師的 ID 尋找該名老師開設的所有課程
     * Spring Data JPA 會自動解析為：SELECT * FROM courses WHERE tutor_id = ?
     */
    List<Course> findByTutorId(Long tutorId);

    /**
     * (選填) 如果你想確保顯示順序，例如按價格從低到高
     */
    List<Course> findByTutorIdOrderByPriceAsc(Long tutorId);
}