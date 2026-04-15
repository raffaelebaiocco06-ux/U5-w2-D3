package raffaele.u5w2d1.service;

import org.springframework.stereotype.Service;
import raffaele.u5w2d1.entities.Autore;
import raffaele.u5w2d1.payload.PayloadAutore;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoreService {

    private List<Autore> autoreDB = new ArrayList<>();

    public List<Autore> findAll() {
        return this.autoreDB;
    }

    public Autore salvaAutore(PayloadAutore body) {
        Autore newAutore = new Autore(
                autoreDB.size() + 1,
                "https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome(),
                body.getNascita(),
                body.getEmail(),
                body.getCognome(),
                body.getNome()
        );

        this.autoreDB.add(newAutore);
        return newAutore;
    }

    public Autore findById(long id) {
        for (Autore autore : autoreDB) {
            if (autore.getId() == id) return autore;
        }
        throw new RuntimeException("id non trovato");
    }

    public Autore findByIdAndUpdate(long id, PayloadAutore body) {
        Autore found = findById(id);

        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setNascita(body.getNascita());

        return found;
    }

    public void findByIdAndDelete(long id) {
        Autore found = findById(id);
        autoreDB.remove(found);
    }
}