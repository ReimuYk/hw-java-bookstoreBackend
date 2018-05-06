package restbs.rest.bookstore;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("oRepo")
public interface orderformRepository extends CrudRepository<orderform,Long>{
    List<orderform> findByUserid(Long userid);
    List<orderform> findByOrderid(String orderid);
}
