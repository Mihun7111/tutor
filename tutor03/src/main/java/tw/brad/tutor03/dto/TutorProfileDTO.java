package tw.brad.tutor03.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tw.brad.tutor03.entity.Review;
import tw.brad.tutor03.entity.TutorSchedule;

@Getter
@Setter
public class TutorProfileDTO {
    private String name;
    private String headline;
    private String avatar;
    private String intro;
    private String certificate_name_1;
    private String videoUrl1;
    private List<TutorSchedule> schedules;
    private List<Review> reviews;
    private Double averageRating;
}
