package raffaele.u5w2d1.repositori;

import org.springframework.data.jpa.repository.JpaRepository;
import raffaele.u5w2d1.entities.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}