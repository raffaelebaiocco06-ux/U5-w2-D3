package raffaele.u5w2d1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import raffaele.u5w2d1.entities.Autore;
import raffaele.u5w2d1.entities.Blog;
import raffaele.u5w2d1.payload.PayloadBlog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BlogService {

    private List<Blog> blogDB = new ArrayList<>();
    private final AutoreService autoreService;

    @Autowired
    public BlogService(AutoreService autoreService) {
        this.autoreService = autoreService;
    }

    public Page<Blog> findAll(int page, int size, String sortBy) {
        if (size > 100 || size <= 0) size = 10;
        if (page < 0) page = 0;

        List<Blog> sortedBlogs = new ArrayList<>(this.blogDB);

        // ordinamento
        switch (sortBy.toLowerCase()) {
            case "titolo":
                sortedBlogs.sort(Comparator.comparing(Blog::getTitolo));
                break;
            case "categoria":
                sortedBlogs.sort(Comparator.comparing(Blog::getCategoria));
                break;
            case "tempolettura":
                sortedBlogs.sort(Comparator.comparing(Blog::getTempoLettura));
                break;
            default:
                sortedBlogs.sort(Comparator.comparing(Blog::getId));
        }

        Pageable pageable = PageRequest.of(page, size);

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), sortedBlogs.size());

        if (start > sortedBlogs.size()) {
            return new PageImpl<>(new ArrayList<>(), pageable, sortedBlogs.size());
        }

        List<Blog> pageContent = sortedBlogs.subList(start, end);

        return new PageImpl<>(pageContent, pageable, sortedBlogs.size());
    }

    public Blog salvaBlog(PayloadBlog body) {
 Autore autore =autoreService.findById(body.getAutoreId());
        Blog newBlog = new Blog(
                blogDB.size() + 1,
                body.getCategoria(),
                body.getTitolo(),
                body.getContenuto(),
                body.getTempoLettura(),
                autore
        );

        this.blogDB.add(newBlog);

        System.out.println("Blog con id " + newBlog.getId() + " creato!");
        return newBlog;
    }

    public Blog findById(long id) {
        for (Blog blog : blogDB) {
            if (blog.getId() == id) return blog;
        }
        throw new RuntimeException("id non trovato");
    }

    public Blog findByIdAndUpdate(long id, PayloadBlog body) {
        Blog found = findById(id);

        found.setCategoria(body.getCategoria());
        found.setTitolo(body.getTitolo());
        found.setContenuto(body.getContenuto());
        found.setTempoLettura(body.getTempoLettura());

        return found;
    }

    public void findByIdAndDelete(long id) {
        Blog found = findById(id);
        blogDB.remove(found);
    }
}
