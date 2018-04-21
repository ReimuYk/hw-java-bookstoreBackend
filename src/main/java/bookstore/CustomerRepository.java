package bookstore;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<tt,Long> {
    List<tt> findByLastName(String lastName);
    List<tt> findByFirstName(String firstName);
}
