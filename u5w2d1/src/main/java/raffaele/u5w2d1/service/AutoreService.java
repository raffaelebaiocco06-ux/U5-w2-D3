package raffaele.u5w2d1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raffaele.u5w2d1.entities.Autore;
import raffaele.u5w2d1.execptionnn.NotFoundException;
import raffaele.u5w2d1.payload.PayloadAutore;
import raffaele.u5w2d1.repositori.AutoreRepository;

import java.util.List;

@Service
public class AutoreService {

    private final AutoreRepository autoreRepository;

    @Autowired
    public AutoreService(AutoreRepository autoreRepository) {
        this.autoreRepository = autoreRepository;
    }

    public List<Autore> findAll() {
        return this.autoreRepository.findAll();
    }

    public Autore salvaAutore(PayloadAutore body) {
        Autore newAutore = new Autore();
        newAutore.setNome(body.getNome());
        newAutore.setCognome(body.getCognome());
        newAutore.setEmail(body.getEmail());
        newAutore.setNascita(body.getNascita());
        newAutore.setAvatar("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());

        return this.autoreRepository.save(newAutore);
    }

    public Autore findById(long id) {
        return this.autoreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Autore con id " + id + " non trovato"));
    }

    public Autore findByIdAndUpdate(long id, PayloadAutore body) {
        Autore found = findById(id);

        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
        found.setNascita(body.getNascita());
        found.setAvatar("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());

        return this.autoreRepository.save(found);
    }

    public void findByIdAndDelete(long id) {
        Autore found = findById(id);
        this.autoreRepository.delete(found);
    }
}