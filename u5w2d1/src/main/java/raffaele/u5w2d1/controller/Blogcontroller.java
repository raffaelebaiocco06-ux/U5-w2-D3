package raffaele.u5w2d1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import raffaele.u5w2d1.entities.Blog;
import raffaele.u5w2d1.payload.PayloadBlog;
import raffaele.u5w2d1.service.BlogService;

import java.util.List;

@RestController
@RequestMapping("/blogs") // http://localhost:8080/blogs
public class Blogcontroller {
 private final BlogService blogService;

 public Blogcontroller(BlogService blogService){
     this.blogService=blogService;
 }

    @GetMapping //fa la get
    public List<Blog> findAll(){
     return this.blogService.findAll();

    }

    @ResponseStatus(HttpStatus.CREATED) // 201 created
    @PostMapping
    public Blog createBlog(@RequestBody PayloadBlog body){
 return this.blogService.salvaBlog(body);
    }

    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable long id){
        return this.blogService.findById(id);
    }

    @PutMapping("/{id}")
    public Blog updateBlog(@PathVariable long id, @RequestBody PayloadBlog body){
        return this.blogService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long id){
        this.blogService.findByIdAndDelete(id);
    }
}
