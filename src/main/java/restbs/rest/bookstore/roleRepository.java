package restbs.rest.bookstore;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("rRepo")
public interface roleRepository extends CrudRepository<role,Long>{
    List<role> findByEmail(String email);
    List<role> findByNickname(String nickname);
}
