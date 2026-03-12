package tw.brad.tutor03.dto;

import java.util.List;

import lombok.Data;
import tw.brad.tutor03.entity.Review;
import tw.brad.tutor03.entity.TutorSchedule;

@Data
public class TutorProfileDTO {
    private String name;
    private String headline;
    private String avatarUrl;
    private String intro;
    private String certificate;
    private String videoUrl1;
    private List<TutorSchedule> schedules;
    private List<Review> reviews;
    private Double averageRating;
}
