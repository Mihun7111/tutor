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
	        String teacherName, String courseName, Integer subject, Integer level,
	        String priceRange, LocalDate date, Integer hour) {

	    return (root, query, builder) -> {
	        List<Predicate> predicates = new ArrayList<>();

	        // 老師姓名模糊搜尋
	        if (teacherName != null && !teacherName.isEmpty()) {
	            predicates.add(builder.like(root.join("user").get("name"), "%" + teacherName + "%"));
	        }

	        // 課程標題精確搜尋
	        if (courseName != null && !courseName.isEmpty()) {
	            predicates.add(builder.equal(root.get("name"), courseName));
	        }

	        // 科目與等級
	        if (subject != null) predicates.add(builder.equal(root.get("subject"), subject));
	        if (level != null) predicates.add(builder.equal(root.get("level"), level));

	        // 價格區間
	        if (priceRange != null && priceRange.contains("-")) {
	            String[] parts = priceRange.split("-");
	            predicates.add(builder.between(root.get("price"),
	                    Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
	        }

	        // 日期與小時 (Join Lesson)
	        if (date != null || hour != null) {
	            Join<Course, Lesson> lessonJoin = root.join("lessons");
	            if (date != null) predicates.add(builder.equal(lessonJoin.get("date"), date));
	            if (hour != null) predicates.add(builder.equal(lessonJoin.get("hour"), hour));
	        }

	        query.distinct(true);
	        return builder.and(predicates.toArray(new Predicate[0]));
	    };
	}
}