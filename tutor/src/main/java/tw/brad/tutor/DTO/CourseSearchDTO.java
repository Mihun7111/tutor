package tw.brad.tutor.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseSearchDTO {
 private String name;        // 來自 users
 private Integer subject;    // 來自 courses
 private Integer level;      // 來自 courses
 private String description; // 來自 courses
 private LocalDate date;     // 來自 lessons
 private Integer hour;       // 來自 lessons
 private Integer price;      // 來自 courses
}
