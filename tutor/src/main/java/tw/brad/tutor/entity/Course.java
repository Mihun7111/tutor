package tw.brad.tutor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")   // 明確指定欄位名稱
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private User user; // 關聯到 users 表獲取老師姓名

    @Column(name = "subject")
    private Integer subject; // 1 英文 / 2 程式

    @Column(name = "level")
    private Integer level;   // 1 初級 / 2 中級 / 3 高級

    @Column(name = "price")
    private Integer price;

    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Lesson> lessons; // 關聯到 lessons 表獲取日期與時段
}
