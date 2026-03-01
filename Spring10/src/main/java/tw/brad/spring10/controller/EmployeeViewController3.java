package tw.brad.spring10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller; // 改用 @Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.brad.spring10.entity.Employee;
import tw.brad.spring10.repo.EmployeeRepo;
import tw.brad.spring10.spec.EmployeeSpec;

@Controller 
@RequestMapping("/3")
public class EmployeeViewController3 {

    @Autowired
    private EmployeeRepo repo;

    @GetMapping("/view3/employees/low/{low}/name/{name}/page/{page}/size/{size}")
    public String showEmployees(
            @PathVariable Float low,
            @PathVariable String name,
            @PathVariable int page,
            @PathVariable int size,
            Model model) {

        String searchName = (name != null && !name.isEmpty()) ? "%" + name + "%" : "%";

        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = repo.findAll(EmployeeSpec.salaryAndName(low, searchName), pageable);

        model.addAttribute("employees", employees.getContent());
        model.addAttribute("currentLow", low);
        model.addAttribute("currentName", name);
        model.addAttribute("salaryLevels", List.of(1000, 2000, 3000));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employees.getTotalPages());
        model.addAttribute("currentSize", size);

        return "empList";
    }
}
