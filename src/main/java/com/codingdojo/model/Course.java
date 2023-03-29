package com.codingdojo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "_courses")
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Long id;

    @NotNull
    @NotEmpty(message = "Name is required.")
    private String name;

    @NotNull
    @NotEmpty(message = "Name instructor is required.")
    private String instructor;

    @NotNull
    private Integer capacity;

    private Integer register;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_has_courses", joinColumns = @JoinColumn(name = "id_course"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> usersJoined = new ArrayList<>();


    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CourseHasUsers> courseHasUsers;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }


}
