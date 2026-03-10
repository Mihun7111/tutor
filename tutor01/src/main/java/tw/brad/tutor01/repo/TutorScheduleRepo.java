package tw.brad.tutor01.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.brad.tutor01.entity.TutorSchedule;

@Repository
public interface TutorScheduleRepo extends JpaRepository<TutorSchedule, Long> {
    // 透過 Spring Data 的命名慣例自動生成查詢：找特定老師、按星期排序、再按小時排序
    List<TutorSchedule> findByTutorIdOrderByWeekdayAscHourAsc(Long tutorId);
}
