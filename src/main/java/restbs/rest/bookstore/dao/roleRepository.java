package restbs.rest.bookstore.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restbs.rest.bookstore.model.role;

import java.util.List;

@Repository("rRepo")
public interface roleRepository extends CrudRepository<role,Long>{
    List<role> findById(Long id);
    List<role> findByEmail(String email);
    List<role> findByNickname(String nickname);
}
