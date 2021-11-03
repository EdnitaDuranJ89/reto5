
package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.modelo.ContadorClientes;
import co.usa.ciclo3.ciclo3.modelo.Reservation;
import co.usa.ciclo3.ciclo3.modelo.StatusReservas;
import co.usa.ciclo3.ciclo3.repository.ReservationRepository;
import static com.fasterxml.jackson.databind.cfg.CoercionInputShape.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date; 
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    
    public List<Reservation> getAll() {
        return (List<Reservation>) reservationRepository.getAll();
    }
  
    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
        }
  
    public Reservation save(Reservation reservation) { 
        if (reservation.getIdReservation()== null){
            return reservationRepository.save(reservation);
        }
        else
        {
            Optional<Reservation> co =  reservationRepository.getReservation(reservation.getIdReservation());
            if (co.isEmpty()){
                return reservationRepository.save(reservation);
            }
            else
            {
                return reservation;
            }
        }
 
    }
    
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> e= reservationRepository.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                reservationRepository.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    public StatusReservas getReporteStatusReservaciones(){
        List<Reservation>completed= reservationRepository.ReservacionStatus("completed");
        List<Reservation>cancelled= reservationRepository.ReservacionStatus("cancelled");
        return new StatusReservas(completed.size(), cancelled.size());
    }
    
    public List<Reservation> getReportesTiempoReservaciones(String datoA, String datoB){ 
        SimpleDateFormat parser=new SimpleDateFormat ("yyyy-MM-dd");
        Date datoUno = new Date();
        Date datoDos = new Date();
        
        try{
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return reservationRepository.ReservacionTiempo(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        }
    }
    public List<ContadorClientes> servicioTopClientes(){
            return reservationRepository.getTopClientes();
        }
}