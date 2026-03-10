package tw.brad.tutor01.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.brad.tutor01.entity.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    // 這裡假設你的 Review Entity 中有關聯一個 Tutor 物件或 tutorId
    List<Review> findByTutorIdOrderByUpdatedAtDesc(Long tutorId);
}
