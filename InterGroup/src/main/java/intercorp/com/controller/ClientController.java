package intercorp.com.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import intercorp.com.domain.Client;
import intercorp.com.dto.ClientDto;
import intercorp.com.dto.ClientKPIDto;
import intercorp.com.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private IClientService service;

    private Logger log = LoggerFactory.getLogger(ClientController.class);

    @PostMapping("/crearCliente")
    public ResponseEntity<Client> saveClient(@RequestBody ClientDto clientDto) throws JsonProcessingException {
        Client client = service.save(clientDto);
        if (client != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(client);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/listarClientes")
    public ResponseEntity<List<ClientDto>> findAll() throws JsonProcessingException {
        List<ClientDto> listClientDto = service.findAll();
        if (listClientDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(listClientDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/kpideclientes")
    public ResponseEntity<ClientKPIDto> getAllKPI() throws JsonProcessingException {
        ClientKPIDto clientKPIDto = service.getAllKPI();
        if (clientKPIDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(clientKPIDto);
        }
        return ResponseEntity.notFound().build();
    }
}
