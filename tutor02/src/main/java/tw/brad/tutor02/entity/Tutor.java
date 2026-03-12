package tw.brad.tutor02.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tutors")
@Data

public class Tutor {

    @Id
    private Long id;

    @OneToOne
    @MapsId // 讓此 ID 同時作為外鍵指向 User 的 ID
    @JoinColumn(name = "id")
    private User user;

    @Column(length = 1000)
    private String intro;

    @Column(length = 500)
    private String certificate;

    @Column(name = "video_url_1", length = 500)
    private String videoUrl1;

    @Column(name = "video_url_2", length = 500)
    private String videoUrl2;

    @Column(name = "bank_code", length = 10)
    private String bankCode;

    @Column(name = "bank_account", length = 20)
    private String bankAccount;

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

    @Column(length = 255)
    private String headline;
}
