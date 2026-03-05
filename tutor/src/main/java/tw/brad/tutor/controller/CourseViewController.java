package tw.brad.tutor.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.brad.tutor.entity.Course;
import tw.brad.tutor.repo.CourseRepo;
import tw.brad.tutor.spec.CourseSpec;

@Controller
public class CourseViewController {

 @Autowired
 private CourseRepo courseRepo;

 @GetMapping("/view/courses")
 public String searchCourses(
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(required = false) String name,
         @RequestParam(required = false) Integer subject,
         @RequestParam(required = false) Integer level,
         @RequestParam(required = false) String priceRange,
         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
         @RequestParam(required = false) Integer hour,
         Model model) {

     Pageable pageable = PageRequest.of(page, 10); // 每頁10筆
     
     // 呼叫動態篩選器
     Page<Course> coursePage = courseRepo.findAll(
         CourseSpec.filterCourses(name, subject, level, priceRange, date, hour), 
         pageable
     );

     model.addAttribute("courses", coursePage.getContent());
     model.addAttribute("totalPages", coursePage.getTotalPages());
     model.addAttribute("currentPage", page);

     // 保留狀態（回傳給前端 form 使用）
     model.addAttribute("currentName", name);
     model.addAttribute("currentSubject", subject);
     model.addAttribute("currentLevel", level);
     model.addAttribute("currentPriceRange", priceRange);
     model.addAttribute("currentDate", date);
     model.addAttribute("currentHour", hour);

     return "courseList"; // 返回您之前預覽的 HTML 頁面
 }
}