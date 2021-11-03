package co.usa.ciclo3.ciclo3.modelo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientCrud extends CrudRepository<Client, Integer> {
    
}
