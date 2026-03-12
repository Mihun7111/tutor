package tw.brad.tutor03.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.brad.tutor03.entity.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    // 根據 courseId 查詢
    List<Review> findByCourseIdOrderByUpdatedAtDesc(Long courseId);
}
