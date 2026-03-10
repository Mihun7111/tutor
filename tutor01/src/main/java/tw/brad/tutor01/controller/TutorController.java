package tw.brad.tutor01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.brad.tutor01.entity.Review;
import tw.brad.tutor01.entity.Tutor;
import tw.brad.tutor01.entity.TutorSchedule;
import tw.brad.tutor01.service.TutorService;

@Controller
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService; // 假設你已寫好對應的 Service

    /**
     * 展示老師個人頁面
     * @param id 老師的 User ID
     */
    @GetMapping("/{id}")
    public String getTutorProfile(@PathVariable Long id, Model model) {
        
        // 1. 取得老師核心資料 (包含 user 資料)
        Tutor tutor = tutorService.findTutorById(id);
        
        if (tutor == null) {
            return "error/404"; // 若找不到老師則導向錯誤頁面
        }

        // 2. 取得老師的課表 (tutor_schedules)
        List<TutorSchedule> schedules = tutorService.findSchedulesByTutorId(id);

        // 3. 取得老師的評價 (reviews)
        List<Review> reviews = tutorService.findReviewsByTutorId(id);

        // 4. 計算平均評分
        double avgRating = reviews.stream()
                                  .mapToInt(Review::getRating)
                                  .average()
                                  .orElse(0.0);

        // 將資料放入 Model 傳遞給前端 Thymeleaf 模板
        model.addAttribute("tutor", tutor);
        model.addAttribute("schedules", schedules);
        model.addAttribute("reviews", reviews);
        model.addAttribute("avgRating", String.format("%.1f", avgRating));

        return "tutor_profile"; // 回傳 templates/tutor_profile.html
    }
}
