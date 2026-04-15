package raffaele.u5w2d1.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PayloadAutore {
    private String nome;
    private String cognome;
    private String email;
    private LocalDate nascita;
}