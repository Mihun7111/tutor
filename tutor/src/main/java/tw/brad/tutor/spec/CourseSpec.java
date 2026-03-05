package tw.brad.tutor.spec;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import tw.brad.tutor.entity.Course;
import tw.brad.tutor.entity.Lesson;

//CourseSpec.java
public class CourseSpec {
 public static Specification<Course> filterCourses(
         String name, Integer subject, Integer level, String priceRange, LocalDate date, Integer hour) {
     
     return (root, query, builder) -> {
         List<Predicate> predicates = new ArrayList<>();

         // 1. 姓名模糊搜尋 (Join User)
         if (name != null && !name.isEmpty()) {
             predicates.add(builder.like(root.join("user").get("name"), "%" + name + "%"));
         }

         // 2. 科目與等級精確搜尋
         if (subject != null) predicates.add(builder.equal(root.get("subject"), subject));
         if (level != null) predicates.add(builder.equal(root.get("level"), level));

         // 3. 費用區間 (300-500, 500-700...)
         if (priceRange != null && priceRange.contains("-")) {
             String[] parts = priceRange.split("-");
             predicates.add(builder.between(root.get("price"), 
                            Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
         }

         // 4. 日期與小時搜尋 (Join Lesson)
         if (date != null || hour != null) {
             Join<Course, Lesson> lessonJoin = root.join("lessons");
             if (date != null) predicates.add(builder.equal(lessonJoin.get("date"), date));
             if (hour != null) predicates.add(builder.equal(lessonJoin.get("hour"), hour));
         }

         // 避免重複結果 (因為一個課程可能有多個 Lesson)
         query.distinct(true);

         return builder.and(predicates.toArray(new Predicate[0]));
     };
 }
}