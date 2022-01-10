package org.example.rbc.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Racing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Timestamp start;
    private LocalDateTime longsFor;
    private byte[] image;

    public Racing(String name, String description, Timestamp start, LocalDateTime longsFor, byte[] image) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.longsFor = longsFor;
        this.image = image;
    }
}
