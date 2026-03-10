package tw.brad.tutor01.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User student; // 評價的學生

    @Column(name = "course_id", nullable = false)
    private Long courseId; // 關聯課程 ID

    @Column(nullable = false)
    private Integer rating; // 1-5 分

    @Column(length = 1000)
    private String comment;

    @Column(name = "updated_at", updatable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id") // 確保資料庫有這一欄，或根據你的業務邏輯調整
    private Tutor tutor;
}
