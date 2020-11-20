package com.studyolle.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

}
