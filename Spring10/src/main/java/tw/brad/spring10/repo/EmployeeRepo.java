package tw.brad.spring10.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.brad.spring10.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee>{

    // SQL 查詢
    @Query(value = """
        SELECT * FROM employees WHERE Salary < :high
        """, nativeQuery = true)
    List<Employee> test1(@Param("high") Float high);
    
    // JPQL 查詢
    @Query("SELECT e FROM Employee e WHERE e.salary < :high")
    List<Employee> test2(@Param("high") Float high);
    
    // 查詢薪資低於指定值的員工
    List<Employee> findBySalaryLessThan(Float high);

    // 查詢薪資高於指定值，且名字包含指定字串的員工
    List<Employee> findBySalaryGreaterThanAndFirstNameContaining(Float salary, String name);
    
    Page<Employee> findAll(Pageable pageable);



}
