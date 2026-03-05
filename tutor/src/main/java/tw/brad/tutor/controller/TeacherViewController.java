package tw.brad.tutor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.brad.tutor.entity.EnglishTeacher;
import tw.brad.tutor.repo.TeacherRepo;
import tw.brad.tutor.spec.TeacherSpec;
import tw.brad.tutor.util.TranslationUtils;

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
            @RequestParam(required = false) String feeRange, // 接收區間字串
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<EnglishTeacher> teachers = repo.findAll(
                TeacherSpec.filterTeachers(age, need, day, time, nation, feeRange), pageable);

        model.addAttribute("teachers", teachers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", teachers.getTotalPages());
        model.addAttribute("utils", new TranslationUtils()); // 提供轉換工具

        // 保留搜尋條件
        model.addAttribute("currentAge", age);
        model.addAttribute("currentNeed", need);
        model.addAttribute("currentDay", day);
        model.addAttribute("currentTime", time);
        model.addAttribute("currentNation", nation);
        model.addAttribute("currentFeeRange", feeRange); // 回傳給前端保持選取狀態

        return "teacherList";
    }
}
