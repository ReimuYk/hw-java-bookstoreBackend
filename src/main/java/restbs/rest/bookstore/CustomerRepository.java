package restbs.rest.bookstore;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("cRepo")
public interface CustomerRepository extends CrudRepository<book,Long> {
}
