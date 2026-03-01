package tw.brad.tutor.spec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import tw.brad.tutor.entity.EnglishTeacher;

public class TeacherSpec {
    public static Specification<EnglishTeacher> filterTeachers(
            String age, String need, String day, String time, String nation, String feeRange) {
        
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
            
            if (feeRange != null && !feeRange.isEmpty()) {
                if (feeRange.contains("-")) {
                    String[] parts = feeRange.split("-");
                    int min = Integer.parseInt(parts[0]);
                    int max = Integer.parseInt(parts[1]);
                    predicates.add(builder.between(root.get("fee"), min, max));
                } else if (feeRange.equals("800plus")) {
                    predicates.add(builder.greaterThan(root.get("fee"), 800));
                }
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
