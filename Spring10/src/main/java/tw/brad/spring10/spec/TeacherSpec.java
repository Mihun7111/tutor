package tw.brad.spring10.spec;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import tw.brad.spring10.entity.EnglishTeacher;

public class TeacherSpec {
    public static Specification<EnglishTeacher> filterTeachers(
            String age, String need, String day, String time, String nation, Integer maxFee) {
        
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (age != null && !age.isEmpty()) 
                predicates.add(builder.like(root.get("studentAge"), "%" + age + "%"));
            
            if (need != null && !need.isEmpty()) 
                predicates.add(builder.like(root.get("learningNeed"), "%" + need + "%"));
            
            if (day != null && !day.isEmpty()) 
                predicates.add(builder.like(root.get("availableDays"), "%" + day + "%"));
            
            if (time != null && !time.isEmpty()) 
                predicates.add(builder.like(root.get("availableTime"), "%" + time + "%"));
            
            if (nation != null && !nation.isEmpty()) 
                predicates.add(builder.equal(root.get("nationality"), nation));
            
            if (maxFee != null && maxFee > 0) 
                predicates.add(builder.lessThanOrEqualTo(root.get("fee"), maxFee));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
