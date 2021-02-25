package intercorp.com.service;

import intercorp.com.domain.Client;
import intercorp.com.dto.ClientDto;
import intercorp.com.dto.ClientKPIDto;
import java.util.List;

public interface IClientService {

    public ClientDto findById (Integer id );
    public Client save (ClientDto client);
    public List<ClientDto> findAll ();
    public ClientKPIDto getAllKPI();
}
