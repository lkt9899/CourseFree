package com.a603.ofcourse.domain.course.domain;

import com.a603.ofcourse.domain.course.enums.CourseCategory;
import com.a603.ofcourse.domain.post.domain.Post;
import com.a603.ofcourse.domain.schedule.domain.Schedule;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(
        name = "course",
        uniqueConstraints = {
                @UniqueConstraint( columnNames = "hash_key")
        })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {
    @Id
    @Column(name = "course_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private CourseCategory courseCategory;

    @Size(max = 30)
    @Column(name = "title", length = 30)
    private String title;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Size(max = 256)
    @Column(name = "hash_key", length = 256)
    private String hashKey;

    @OneToOne
    @JoinColumn(name = "course_characteristic_id")
    private CourseCharacteristic courseCharacteristic;

    @Column(name="use_count")
    private Integer useCount;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<MyCourse> myCourseList = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CourseReview> courseReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CoursePlace> coursePlaceList = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Post> postList = new ArrayList<>();


    @Builder
    public Course(CourseCategory courseCategory,
                  String title,
                  String imageUrl,
                  String hashKey,
                  Integer useCount) {
        this.courseCategory = courseCategory;
        this.title = title;
        this.imageUrl = imageUrl;
        this.hashKey = hashKey;
        this.useCount = useCount;
    }

    public void updateHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public void updateUseCount() {
        this.useCount += 1;
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}