package tw.brad.tutor.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.brad.tutor.entity.Course;
import tw.brad.tutor.entity.Lesson;
import tw.brad.tutor.repo.CourseRepo;
import tw.brad.tutor.repo.LessonRepo;
import tw.brad.tutor.spec.CourseSpec;

@Controller
public class CourseViewController {

 @Autowired
 private CourseRepo courseRepo;

 @GetMapping("/view/courses")
 public String searchCourses(
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(required = false) String teacherName,
         @RequestParam(required = false) String courseName,
         @RequestParam(required = false) Integer subject,
         @RequestParam(required = false) Integer level,
         @RequestParam(required = false) String priceRange,
         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
         @RequestParam(required = false) Integer hour,
         Model model) {

     Pageable pageable = PageRequest.of(page, 10);

     Page<Course> coursePage = courseRepo.findAll(
         CourseSpec.filterCourses(teacherName, courseName, subject, level, priceRange, date, hour),
         pageable
     );

     model.addAttribute("courses", coursePage.getContent());
     model.addAttribute("totalPages", coursePage.getTotalPages());
     model.addAttribute("currentPage", page);

     // 保留狀態
     model.addAttribute("currentTeacherName", teacherName);
     model.addAttribute("currentCourseName", courseName);
     model.addAttribute("currentSubject", subject);
     model.addAttribute("currentLevel", level);
     model.addAttribute("currentPriceRange", priceRange);
     model.addAttribute("currentDate", date);
     model.addAttribute("currentHour", hour);

     return "courseList3";
 }
 @Autowired
 private LessonRepo lessonRepo;
 
 @GetMapping("/view/teacher_schedule/{teacherId}")
 @ResponseBody
 public Map<Integer, Map<Integer, String>> getTeacherSchedule(@PathVariable Long teacherId) {
     List<Lesson> lessons = lessonRepo.findByUserId(teacherId);

     // Map<星期幾(1=Mon...7=Sun), Map<小時, 顏色class>>
     Map<Integer, Map<Integer, String>> schedule = new HashMap<>();

     for (Lesson lesson : lessons) {
         int weekday = lesson.getDate().getDayOfWeek().getValue(); // 1=Mon ... 7=Sun
         schedule.putIfAbsent(weekday, new HashMap<>());

         String colorClass;
         switch (lesson.getStatus()) {
             case 0: colorClass = "bg-available"; break;
             case 1: colorClass = "bg-booked"; break;
             case 2: colorClass = "bg-unavailable"; break;
             case 3: colorClass = "bg-unavailable"; break;
             default: colorClass = "bg-unavailable"; break;
         }

         schedule.get(weekday).put(lesson.getHour(), colorClass);
     }

     return schedule;
 }
}