package co.usa.ciclo3.ciclo3.web;

import co.usa.ciclo3.ciclo3.modelo.Motorbike;
import co.usa.ciclo3.ciclo3.service.MotorbikeService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping(value ="/api/Motorbike")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})


public class MotorbikeController {
    
    @Autowired
    private MotorbikeService motorbikeService;
    
    @GetMapping("/all")
    public List<Motorbike> getMotorbike(){
        return motorbikeService.getAll();
    } 
    
    @GetMapping("/{id}")
    public Optional <Motorbike> getMotorbikes(@PathVariable("id")int id){
        return motorbikeService.getMotorbike(id);
    }
    
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    
    public Motorbike save(@RequestBody Motorbike motorbike){
        return motorbikeService.save(motorbike);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Motorbike update(@RequestBody Motorbike motorbike) {
        return motorbikeService.update(motorbike);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteMotorbike(@PathVariable("id") int motorbikeId) {
        return motorbikeService.deleteMotorbike(motorbikeId);
    } 
}
