package tw.brad.tutor01.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.brad.tutor01.entity.Review;
import tw.brad.tutor01.entity.Tutor;
import tw.brad.tutor01.entity.TutorSchedule;
import tw.brad.tutor01.repo.ReviewRepo;
import tw.brad.tutor01.repo.TutorRepo;
import tw.brad.tutor01.repo.TutorScheduleRepo;

@Service
public class TutorService {

    @Autowired
    private TutorRepo tutorRepository;

    @Autowired
    private TutorScheduleRepo scheduleRepository;

    @Autowired
    private ReviewRepo reviewRepository;

    /**
     * 取得老師完整檔案（包含 User 基本資料）
     */
    public Tutor findTutorById(Long id) {
        return tutorRepository.findById(id).orElse(null);
    }

    /**
     * 取得特定老師的所有開放時段，並依照星期與小時排序
     */
    public List<TutorSchedule> findSchedulesByTutorId(Long tutorId) {
        // 建議在 Repository 寫一個依據 tutorId 查詢的方法
        return scheduleRepository.findByTutorIdOrderByWeekdayAscHourAsc(tutorId);
    }

    /**
     * 取得該老師的所有評價
     */
    public List<Review> findReviewsByTutorId(Long tutorId) {
        // 假設 review 資料表中的 course_id 可以關聯回老師，或者 review 直接有關聯 tutor_id
        // 這裡以你圖中的 course_id 邏輯為準，或直接查詢該老師收到的評論
        return reviewRepository.findByTutorIdOrderByUpdatedAtDesc(tutorId);
    }

    /**
     * 更新老師的標題與頭像（用於個人設定頁面）
     */
    @Transactional
    public void updateTutorProfile(Long id, String headline, String avatarUrl) {
        Tutor tutor = tutorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("找不到該老師"));
        
        tutor.setHeadline(headline);
        tutor.setAvatarUrl(avatarUrl);
        tutorRepository.save(tutor);
    }
}
