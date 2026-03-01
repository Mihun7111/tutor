package tw.brad.spring10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller; // 改用 @Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.brad.spring10.entity.Employee;
import tw.brad.spring10.repo.EmployeeRepo;
import tw.brad.spring10.spec.EmployeeSpec;

@Controller // 注意：不再使用 @RestController，因為我們要回傳頁面名稱
@RequestMapping("/")
public class EmployeeViewController {

    @Autowired
    private EmployeeRepo repo;

    @GetMapping("/view/employees")
    public String showEmployees(
    		@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,

            @RequestParam(required = false, defaultValue = "0") Float low,
            @RequestParam(required = false, defaultValue = "") String name,
            Model model) {
    	// 在後端加上 SQL 的 LIKE 通配符，避免前端傳送 % 造成解碼錯誤
    	String searchName = (name != null && !name.isEmpty()) ? "%" + name + "%" : "%";
        
    	Pageable pageable = PageRequest.of(page, size);
    	
    	Page<Employee> employees = repo.findAll(EmployeeSpec.salaryAndName(low, searchName), pageable);

        model.addAttribute("employees", employees);
        
        model.addAttribute("currentLow", low);
        model.addAttribute("currentName", name);
        model.addAttribute("salaryLevels", List.of(1000, 2000, 3000));

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employees.getTotalPages());
        return "empList"; 
    }
}
