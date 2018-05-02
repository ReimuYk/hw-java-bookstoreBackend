package restbs.rest.bookstore;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cRepo")
public interface CustomerRepository extends CrudRepository<book,Long> {
    List<book> findByName(String name);
    List<book> findByPublish(String publish);
    List<book> findByDate(String date);
}
