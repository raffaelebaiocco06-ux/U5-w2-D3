package raffaele.u5w2d1.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import raffaele.u5w2d1.entities.Autore;

@Setter
@Getter
@ToString
public class PayloadBlog {
    private long id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoLettura;
    private long autoreId;
}
