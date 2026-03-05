package tw.brad.tutor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "booking_id")
    private Long bookingId;   // 訂課ID，可為 NULL

    @Column(name = "date", nullable = false)
    private LocalDate date;   // 上課日期

    @Column(name = "hour", nullable = false)
    private Integer hour;     // 上課時間 9-21

    @Column(name = "status", nullable = false)
    private Integer status;   // 0 available / 1 scheduled / 2 completed / 3 cancelled

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private User user; // 關聯到 users 表，取得老師姓名

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course; // 關聯到 courses 表，取得課程資訊
}