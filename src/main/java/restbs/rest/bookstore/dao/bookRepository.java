package restbs.rest.bookstore.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restbs.rest.bookstore.model.book;

import java.util.List;

@Repository("cRepo")
public interface bookRepository extends CrudRepository<book,Long> {
    List<book> findByName(String name);
    List<book> findByPublish(String publish);
    List<book> findByDate(String date);
}
