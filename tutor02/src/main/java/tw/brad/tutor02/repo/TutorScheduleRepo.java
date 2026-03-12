package tw.brad.tutor02.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.brad.tutor02.entity.TutorSchedule;
import java.util.List;

public interface TutorScheduleRepo extends JpaRepository<TutorSchedule, Long> {
	// 方法名必須對應實體變數名 user，Spring 會自動提取其 ID 進行查詢
	List<TutorSchedule> findByUserId(Long userId);
}
