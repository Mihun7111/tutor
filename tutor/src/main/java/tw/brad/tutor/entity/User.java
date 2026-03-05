package tw.brad.tutor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "role", nullable = false)
    private Integer role;     // 1 student / 2 tutor

    @Column(name = "is_admin")
    private Integer isAdmin;  // 1 admin

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "wallet")
    private Long wallet;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Course> courses; // 老師開設的課程

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Lesson> lessons; // 老師的課堂
}