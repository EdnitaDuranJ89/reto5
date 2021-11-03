
package co.usa.ciclo3.ciclo3.web;

import co.usa.ciclo3.ciclo3.modelo.Message;
import co.usa.ciclo3.ciclo3.service.MessageService;
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
@RequestMapping(value ="/api/Message")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})


public class MessageController {
    
    @Autowired
    private MessageService messageService;
    
    @GetMapping("/all")
    public List<Message> getMessage(){
        return messageService.getAll();
    } 
    
    @GetMapping("/{id}")
    public Optional <Message> getMessages(@PathVariable("id")int id){
        return messageService.getMessage(id);
    }
    
    
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message save(@RequestBody Message message){
        return messageService.save(message);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Message update(@RequestBody Message message) {
        return messageService.update(message);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteMessage(@PathVariable("id") int messageId) {
        return messageService.deleteMessage(messageId);
    }
}