package com.springboot.cachingapplication.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity(name = "technology")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "technology_name")
    private String technologyName;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

}
