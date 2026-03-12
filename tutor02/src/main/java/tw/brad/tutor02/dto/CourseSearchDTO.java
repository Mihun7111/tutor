package tw.brad.tutor02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSearchDTO {
    private Long id;
    private String teacherName;
    private String avatarUrl;   // 新增：老師頭像
    private String headline;    // 新增：老師標題
    private String courseName;
    private Integer subject;
    private String description;
    private Integer price;
}
