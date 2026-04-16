package raffaele.u5w2d1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import raffaele.u5w2d1.entities.Autore;
import raffaele.u5w2d1.entities.Blog;
import raffaele.u5w2d1.execptionnn.NotFoundException;
import raffaele.u5w2d1.payload.PayloadBlog;
import raffaele.u5w2d1.repositori.BlogRepository;

@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final AutoreService autoreService;

    @Autowired
    public BlogService(BlogRepository blogRepository, AutoreService autoreService) {
        this.blogRepository = blogRepository;
        this.autoreService = autoreService;
    }

    public Page<Blog> findAll(int page, int size, String sortBy) {
        if (size > 100 || size <= 0) size = 10;
        if (page < 0) page = 0;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.blogRepository.findAll(pageable);
    }

    public Blog salvaBlog(PayloadBlog body) {
        Autore autore = autoreService.findById(body.getAutoreId());

        Blog newBlog = new Blog();
        newBlog.setCategoria(body.getCategoria());
        newBlog.setTitolo(body.getTitolo());
        newBlog.setCover(body.getCover());
        newBlog.setContenuto(body.getContenuto());
        newBlog.setTempoLettura(body.getTempoLettura());
        newBlog.setAutore(autore);

        return this.blogRepository.save(newBlog);
    }

    public Blog findById(long id) {
        return this.blogRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Blog con id " + id + " non trovato"));
    }

    public Blog findByIdAndUpdate(long id, PayloadBlog body) {
        Blog found = findById(id);
        Autore autore = autoreService.findById(body.getAutoreId());

        found.setCategoria(body.getCategoria());
        found.setTitolo(body.getTitolo());
        found.setCover(body.getCover());
        found.setContenuto(body.getContenuto());
        found.setTempoLettura(body.getTempoLettura());
        found.setAutore(autore);

        return this.blogRepository.save(found);
    }

    public void findByIdAndDelete(long id) {
        Blog found = findById(id);
        this.blogRepository.delete(found);
    }
}