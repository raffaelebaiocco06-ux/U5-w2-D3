package raffaele.u5w2d1.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name="blogs")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Blog {
    @Id
    @GeneratedValue
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
    @Column(nullable = false)
    private Autore autore;

    public Blog(long id, String categoria, String titolo, String contenuto, int tempoLettura, Autore autore) {
        this.id = id;//vediamo eh
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = "https://picsum.photos/200/300";
        this.contenuto = contenuto;
        this.tempoLettura = tempoLettura;
        this.autore = autore;
    }
}
