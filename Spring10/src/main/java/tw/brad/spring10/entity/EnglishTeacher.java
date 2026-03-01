package tw.brad.spring10.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "english_teachers")
@Data
public class EnglishTeacher {
    @Id
    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "student_age")
    private String studentAge;

    @Column(name = "learning_need")
    private String learningNeed;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "available_days")
    private String availableDays;

    @Column(name = "available_time")
    private String availableTime;

    @Column(name = "fee")
    private Integer fee;

    @Column(name = "self_intro")
    private String selfIntro;
}
