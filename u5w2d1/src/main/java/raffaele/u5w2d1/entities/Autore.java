package raffaele.u5w2d1.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name="autori")
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Autore {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private LocalDate nascita;
    @Column(nullable = false)
    private String avatar;

    public Autore(long id, String avatar, LocalDate nascita, String email, String cognome, String nome) {
        this.id = id;
        this.avatar = avatar;
        this.nascita = nascita;
        this.email = email;
        this.cognome = cognome;
        this.nome = nome;
    }
}

