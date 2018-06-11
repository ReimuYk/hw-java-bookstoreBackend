package restbs.rest.bookstore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restbs.rest.bookstore.model.orderform;

import java.util.List;

@Repository("oRepo")
public interface orderformRepository extends MongoRepository<orderform,String> {
    List<orderform> findByUserid(Long userid);
    List<orderform> findByOrderid(String orderid);
}
