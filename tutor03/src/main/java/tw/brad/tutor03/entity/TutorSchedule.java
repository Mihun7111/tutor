package tw.brad.tutor03.entity;

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
    private Tutor tutor; // 關聯 Tutor（不直接關聯 User）

    @Column(nullable = false)
    private Integer weekday; // 1: 星期一 … 7: 星期日

    @Column(nullable = false)
    private Integer hour; // 上課時段，9–21
}
