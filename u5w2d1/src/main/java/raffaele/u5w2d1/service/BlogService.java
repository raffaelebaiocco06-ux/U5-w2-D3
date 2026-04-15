package raffaele.u5w2d1.service;

import org.springframework.stereotype.Service;
import raffaele.u5w2d1.entities.Blog;
import raffaele.u5w2d1.payload.PayloadBlog;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    private List<Blog> blogDB = new ArrayList<>();

    public List<Blog> findAll() {
        return this.blogDB;
    }

    public Blog salvaBlog(PayloadBlog body) {

        Blog newBlog = new Blog(
                blogDB.size() + 1, // id automatico
                body.getCategoria(),
                body.getTitolo(),
                body.getContenuto(),
                body.getTempoLettura(),

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
