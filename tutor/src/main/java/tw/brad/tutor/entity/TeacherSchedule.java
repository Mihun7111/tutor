package tw.brad.tutor.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "teacher_schedule")
@Data
public class TeacherSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    // 這裡使用 Integer 對應資料庫的 teacher_id 外鍵
    @Column(name = "teacher_id")
    private Integer teacherId;

    // 對應 SQL: ENUM('Sunday','Monday'...)
    @Column(name = "weekday")
    private String weekday;

    // 對應 SQL: ENUM('morning_06_12','afternoon_12_18'...)
    @Column(name = "time_slot")
    private String timeSlot;
}