package intercorp.com.dao;

import intercorp.com.domain.Client;
import org.springframework.data.repository.CrudRepository;

public interface IClientDao extends CrudRepository<Client, Integer> {
}
