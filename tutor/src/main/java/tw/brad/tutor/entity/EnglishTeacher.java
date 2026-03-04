package tw.brad.tutor.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    
 // EnglishTeacher.java 內
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id", insertable = false, updatable = false)
    private List<TeacherSchedule> schedules;
}
