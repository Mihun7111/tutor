package tw.brad.tutor02.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    private LocalDate birthday;

    @Column(nullable = false)
    private Integer role; // 1=學生, 2=老師

    @Column(name = "is_admin", nullable = false)
    private Integer isAdmin = 0; // 0=否, 1=管理員

    @Column(nullable = false)
    private Long wallet = 0L;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    
    //為了讓 CourseSpec 能順利從課程連動到課表
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<TutorSchedule> schedules;
}
