package tw.brad.spring10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.spring10.entity.Employee;
import tw.brad.spring10.repo.EmployeeRepo;
import tw.brad.spring10.spec.EmployeeSpec;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeRepo repo;

    @RequestMapping("/test1")
    public void test1() {
        repo.test1(2000f).forEach(e -> {
            System.out.printf("%s %s : %s\n",
                e.getFirstName(),
                e.getLastName(),
                e.getSalary());
        });
    }
    @RequestMapping("/test2")
    public void test2() {
        repo.test2(2000f).forEach(e -> {
            System.out.printf("%s %s : %s\n",
                e.getFirstName(),
                e.getLastName(),
                e.getSalary());
        });
    }
    @GetMapping("/test3")
    public List<Employee> test3(@RequestParam Float low, @RequestParam String name) {
        return repo.findAll(EmployeeSpec.salaryAndName(low, name));
    }
    @RequestMapping("/test4")
	public void test4() {
		repo.findAll(Specification.allOf(
				EmployeeSpec.firstNameEquals("Nancy"),
				EmployeeSpec.lastNameEquals(null),
				EmployeeSpec.titleEquals("Sales Representative")
				)).forEach(e -> {
					System.out.printf("%s %s : %s\n", 
							e.getFirstName(), 
							e.getLastName(), 
							e.getTitle());
				});
		System.out.println("----");
		repo.findAll(Specification.anyOf(
				EmployeeSpec.firstNameEquals("Steven"),
				EmployeeSpec.lastNameEquals(null),
				EmployeeSpec.titleEquals("Sales Representative")
				)).forEach(e -> {
					System.out.printf("%s %s : %s\n", 
							e.getFirstName(), 
							e.getLastName(), 
							e.getTitle());
				});
		
	}

}
