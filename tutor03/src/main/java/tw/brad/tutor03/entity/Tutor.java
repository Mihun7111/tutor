package tw.brad.tutor03.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tutors")
@Data
public class Tutor {

    @Id
    private Long id; // 與 users.id 共享（@MapsId）

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column(length = 255)
    private String headline; // 吸睛標題，如「TESL認證英語教師」

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    @Column(columnDefinition = "TEXT")
    private String intro; // 自我介紹

    @Column(length = 500)
    private String certificate; // 專業證照

    @Column(name = "video_url_1", length = 500)
    private String videoUrl1; // 自我介紹影片

    @Column(name = "video_url_2", length = 500)
    private String videoUrl2; // 教學示範影片

    @Column(name = "bank_code", length = 10)
    private String bankCode;

    @Column(name = "bank_account", length = 20)
    private String bankAccount;
}
