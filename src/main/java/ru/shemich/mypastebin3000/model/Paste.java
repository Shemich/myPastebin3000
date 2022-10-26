package ru.shemich.mypastebin3000.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "pastes")
public class Paste {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", length = 6, nullable = false)
    Integer id;
    @Column(name = "text")
    String text;
    @Column(name = "hash")
    String hash;
    @Column(name = "lifetime")
    LocalDateTime lifetime;
    @Column(name = "is_public")
    boolean isPublic;
}
