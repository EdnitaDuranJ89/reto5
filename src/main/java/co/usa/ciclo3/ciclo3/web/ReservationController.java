package co.usa.ciclo3.ciclo3.web;

import co.usa.ciclo3.ciclo3.modelo.ContadorClientes;
import co.usa.ciclo3.ciclo3.modelo.Reservation;
import co.usa.ciclo3.ciclo3.modelo.StatusReservas;
import co.usa.ciclo3.ciclo3.service.ReservationService;
import java.text.ParseException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(value ="/api/Reservation")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})


public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
    @GetMapping("/all")
    public List<Reservation> getReservation(){
        return reservationService.getAll();
    } 
    
    @GetMapping("/{id}")
    public Optional <Reservation> getReservations(@PathVariable("id")int id){
        return reservationService.getReservation(id);
    }
    
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation){
        return reservationService.save(reservation);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation) {
        return reservationService.update(reservation);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteReservation(@PathVariable("id") int reservationId) {
        return reservationService.deleteReservation(reservationId);
    }
    
    //RETO 5
    
     @GetMapping("/report-status")
    public StatusReservas getReservas(){
        return reservationService.getReporteStatusReservaciones();
    }
    
    @GetMapping("/report-dates/{dateOne}/{dateTwo}")
    public List<Reservation> getReservasTiempo (@PathVariable("dateOne")String dateOne, @PathVariable("dateTwo")String dateTwo) throws ParseException{
        return reservationService.getReportesTiempoReservaciones(dateOne, dateTwo);
    }
    
    @GetMapping("/report-clients")
    public List<ContadorClientes> getClientes(){
        return reservationService.servicioTopClientes();
    }
}