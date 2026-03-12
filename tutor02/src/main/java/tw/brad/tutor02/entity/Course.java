package tw.brad.tutor02.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 修改：直接關聯 Tutor 實體
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutor tutor;

    @Column(nullable = false, length = 200)
    private String name;

    /**
     * 科目代碼：
     * 1x: 年級課程 (11低年級, 12中年級, 13高年級)
     * 2x: 檢定與升學 (21GEPT, 22YLE, 23國中先修)
     * 31: 其他
     */
    @Column(nullable = false)
    private Integer subject;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "is_active", nullable = false)
    private Integer isActive = 1; // 1=上架, 0=下架
}
