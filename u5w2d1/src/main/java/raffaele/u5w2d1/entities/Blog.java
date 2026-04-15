package raffaele.u5w2d1.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "blogs")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private String cover;

    @Column(nullable = false)
    private String contenuto;

    @Column(nullable = false)
    private int tempoLettura;

    @ManyToOne
    @JoinColumn(name = "autore_id", nullable = false)
    private Autore autore;

    public Blog(String categoria, String titolo, String cover, String contenuto, int tempoLettura, Autore autore) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoLettura = tempoLettura;
        this.autore = autore;
    }
}
