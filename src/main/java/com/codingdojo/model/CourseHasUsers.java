package com.codingdojo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users_has_courses")
@AllArgsConstructor
@NoArgsConstructor
public class CourseHasUsers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_user")
    private Long idUser;


    @Column(name = "id_course")
    private Long idCourse;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false, insertable = false, updatable = false)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course", nullable = false, insertable = false, updatable = false)
    private Course course;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

}
