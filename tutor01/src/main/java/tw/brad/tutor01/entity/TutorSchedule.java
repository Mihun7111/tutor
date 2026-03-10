package tw.brad.tutor01.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "tutor_schedules", 
       uniqueConstraints = {@UniqueConstraint(columnNames = {"tutor_id", "weekday", "hour"})})
@Data
public class TutorSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @Column(nullable = false)
    private Integer weekday; // 1-7 (星期一至日)

    @Column(nullable = false)
    private Integer hour; // 9-21
}
