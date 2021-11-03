
package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.modelo.Message;
import co.usa.ciclo3.ciclo3.modelo.Motorbike;
import co.usa.ciclo3.ciclo3.modelo.MotorbikeCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;



@Repository
public class MotorbikeRepository {
    
    @Autowired
    private MotorbikeCrud motorbikeCrudRepository;
    
    public List<Motorbike> getAll(){
        return (List<Motorbike>) motorbikeCrudRepository.findAll();
    }
    
    public Optional<Motorbike> getMotorbike(int id){
        return motorbikeCrudRepository.findById(id);
    }
    
    public Motorbike save(Motorbike motorbike){
        return motorbikeCrudRepository.save(motorbike);
    }
    
    public void delete(Motorbike motorbike){
        motorbikeCrudRepository.delete(motorbike);
    }
}