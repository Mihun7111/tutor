package tw.brad.spring10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tw.brad.spring10.entity.EnglishTeacher;
import tw.brad.spring10.repo.TeacherRepo;
import tw.brad.spring10.spec.TeacherSpec;

import java.util.List;

@Controller
public class TeacherViewController {

    @Autowired
    private TeacherRepo repo;

    @GetMapping("/view/teachers")
    public String showTeachers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String need,
            @RequestParam(required = false) String day,
            @RequestParam(required = false) String time,
            @RequestParam(required = false) String nation,
            @RequestParam(defaultValue = " ") Integer maxFee,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<EnglishTeacher> teachers = repo.findAll(
                TeacherSpec.filterTeachers(age, need, day, time, nation, maxFee), pageable);

        model.addAttribute("teachers", teachers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", teachers.getTotalPages());
        
        // 傳回搜尋條件以便在頁面保留狀態
        model.addAttribute("currentAge", age);
        model.addAttribute("currentNeed", need);
        model.addAttribute("currentNation", nation);
        model.addAttribute("currentMaxFee", maxFee);

        return "teacherList"; // 對應 Thymeleaf 頁面名稱
    }
}