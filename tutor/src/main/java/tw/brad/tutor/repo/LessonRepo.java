package tw.brad.tutor.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import tw.brad.tutor.entity.Lesson;

public interface LessonRepo extends JpaRepository<Lesson, Long> {
    // 根據老師 ID 查詢所有課堂
    List<Lesson> findByUserId(Long userId);

    // 根據課程 ID 查詢所有課堂
    List<Lesson> findByCourseId(Long courseId);
}
