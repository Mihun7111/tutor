package tw.brad.tutor03.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.brad.tutor03.entity.Course;
import tw.brad.tutor03.entity.Review;
import tw.brad.tutor03.entity.Tutor;
import tw.brad.tutor03.entity.TutorSchedule;
import tw.brad.tutor03.repo.CourseRepo;
import tw.brad.tutor03.repo.ReviewRepo;
import tw.brad.tutor03.repo.TutorRepo;
import tw.brad.tutor03.repo.TutorScheduleRepo;

@Service
public class TutorService {

    @Autowired
    private TutorRepo tutorRepo;
    
    @Autowired 
    private CourseRepo courseRepo; // 新增

    @Autowired
    private TutorScheduleRepo scheduleRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    /**
     * 取得老師完整檔案（包含 User 基本資料）
     */
    public Tutor findTutorById(Long id) {
        return tutorRepo.findById(id).orElse(null);
    }

    /**
     * 取得特定老師的所有開放時段，並依照星期與小時排序
     */
    public List<TutorSchedule> findSchedulesByTutorId(Long tutorId) {
        // 建議在 Repository 寫一個依據 tutorId 查詢的方法
        return scheduleRepo.findByTutorIdOrderByWeekdayAscHourAsc(tutorId);
    }

    // 取得老師的所有課程
    public List<Course> findCoursesByTutorId(Long tutorId) {
        return courseRepo.findByTutorId(tutorId);
    }

    // 根據課程 ID 取得該課的評價
    public List<Review> findReviewsByCourseId(Long courseId) {
        return reviewRepo.findByCourseIdOrderByUpdatedAtDesc(courseId);
    }
    
    // 取得單一課程資訊
    public Course findCourseById(Long courseId) {
        return courseRepo.findById(courseId).orElse(null);
    }

    /**
     * 更新老師的標題與頭像（用於個人設定頁面）
     */
    @Transactional
    public void updateTutorProfile(Long id, String headline, String avatarUrl) {
        Tutor tutor = tutorRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("找不到該老師"));
        
        tutor.setHeadline(headline);
        tutor.setAvatarUrl(avatarUrl);
        tutorRepo.save(tutor);
    }
}
