package tw.brad.tutor03.spec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import tw.brad.tutor03.entity.Course;

public class CourseSpec {
    public static Specification<Course> filterCourses(
            String teacherName, String courseName, Integer subject, String priceRange, 
            Integer weekday, String timeSlot) {

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. 強制過濾：只顯示上架課程
            predicates.add(builder.equal(root.get("isActive"), 1));

            // 2. 老師姓名模糊搜尋 (Join User 表)
            if (teacherName != null && !teacherName.isEmpty()) {
            	predicates.add(builder.like(
                        root.join("tutor").join("user").get("name"), 
                        "%" + teacherName + "%"
                    ));
            }

            // 3. 課程名稱模糊搜尋
            if (courseName != null && !courseName.isEmpty()) {
                predicates.add(builder.like(root.get("name"), "%" + courseName + "%"));
            }

            // 4. 科目代碼精確搜尋 (11, 12, 21...)
            if (subject != null) {
                predicates.add(builder.equal(root.get("subject"), subject));
            }

            // 5. 價格區間過濾 (格式: "min-max")
            if (priceRange != null && priceRange.contains("-")) {
                String[] parts = priceRange.split("-");
                try {
                    int min = Integer.parseInt(parts[0]);
                    int max = Integer.parseInt(parts[1]);
                    predicates.add(builder.between(root.get("price"), min, max));
                } catch (NumberFormatException e) {
                    // 價格格式錯誤則忽略
                }
            }
            // 6. 加入每週時間搜尋邏輯
            if (weekday != null || (timeSlot != null && !timeSlot.isEmpty())) {
            	// 這裡的路徑：Course -> tutor -> user -> schedules
                // 注意：你的 schedules 是定義在 User 實體中
                Join<Object, Object> scheduleJoin = root.join("tutor").join("user").join("schedules");
                
                if (weekday != null) {
                    predicates.add(builder.equal(scheduleJoin.get("weekday"), weekday));
                }
                if (timeSlot != null && !timeSlot.isEmpty()) {
                    switch (timeSlot) {
                        case "morning": // 9-13
                            predicates.add(builder.between(scheduleJoin.get("hour"), 9, 12));
                            break;
                        case "afternoon": // 13-17
                            predicates.add(builder.between(scheduleJoin.get("hour"), 13, 16));
                            break;
                        case "evening": // 17-21
                            predicates.add(builder.between(scheduleJoin.get("hour"), 17, 20));
                            break;
                    }
                } 
                // 避免同一個課程因為有多個符合的時段而出現重複結果
                query.distinct(true);
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}