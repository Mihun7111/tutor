package tw.brad.tutor02.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tutor_schedules", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tutor_id", "weekday", "hour"})
})
@Data
public class TutorSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private User user; // 改成對應 User 實體類別
    
    @Column(nullable = false)
    private Integer weekday; // 1=星期一 ... 7=星期日

    @Column(nullable = false)
    private Integer hour; // 上課小時 (9-21)
}
