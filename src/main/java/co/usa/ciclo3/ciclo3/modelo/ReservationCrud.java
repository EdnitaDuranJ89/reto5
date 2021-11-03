
package co.usa.ciclo3.ciclo3.modelo;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationCrud extends CrudRepository<Reservation, Integer> {
    
   
    
    // select clientId, count(*) as "total" from reservacion group by cliendId order by total desc;
    @Query ("SELECT c.client, COUNT(c.client) from Reservation AS c group by c.client order by COUNT(c.client)DESC")
    
    public List<Object[]> countTotalReservacionesByCliente();
    
    public List<Reservation> findAllByStatus(String status);
    
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);
}
