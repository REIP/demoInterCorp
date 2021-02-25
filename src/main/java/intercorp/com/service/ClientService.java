package intercorp.com.service;

import intercorp.com.dao.IClientDao;
import intercorp.com.domain.Client;
import intercorp.com.dto.ClientDto;
import intercorp.com.dto.ClientKPIDto;
import intercorp.com.dto.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private IClientDao clientDao;

    @Override
    public ClientDto findById(Integer id) {
        Client varClient = clientDao.findById(id).orElse(null);
        return new ClientDto(varClient.getId(),
                varClient.getName(),
                varClient.getLastName(),
                varClient.getAge(),
                varClient.getBirthDate(),
                ClientUtil.calculateDeadthAge(varClient));
    }

    @Override
    public Client save(ClientDto clientDto) {
        Client varclient = new Client();
        if (clientDto != null) {
            varclient = new Client();
            varclient.setName(clientDto.getName());
            varclient.setLastName(clientDto.getLastName());
            varclient.setAge(clientDto.getAge());
            varclient.setBirthDate(clientDto.getBirthDate());
        }
        return clientDao.save(varclient);
    }

    @Override
    public ClientKPIDto getAllKPI() {
        List<ClientDto> listClientDto = findAll();
        ClientKPIDto clientKPIDto = null;
        if (listClientDto != null && listClientDto.size() > 0) {
            clientKPIDto = new ClientKPIDto();
            clientKPIDto.setAverageAge(ClientUtil.calculateAverageAge(listClientDto));
            clientKPIDto.setDeviationAge(ClientUtil.calculateDeviationAge(listClientDto));
            return clientKPIDto;
        }
        return null;
    }

    @Override
    public List<ClientDto> findAll() {
        var it = clientDao.findAll();
        if (it != null && ClientUtil.size(it) > 0) {
            List<ClientDto> clients = new ArrayList<ClientDto>();
            it.forEach(e -> {
                clients.add(
                        new ClientDto(e.getId(),
                                e.getName(),
                                e.getLastName(),
                                e.getAge(),
                                e.getBirthDate(),
                                ClientUtil.calculateDeadthAge(e))
                );
            });
            return clients;
        }
        return null;
    }
}
