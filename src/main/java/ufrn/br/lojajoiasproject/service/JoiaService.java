package ufrn.br.lojajoiasproject.service;

import org.springframework.stereotype.Service;
import ufrn.br.lojajoiasproject.domain.Joia;
import ufrn.br.lojajoiasproject.repository.JoiaRepository;

@Service
public class JoiaService {

    JoiaRepository repository;

    public JoiaService(JoiaRepository repository) {
        this.repository = repository;
    }

    public  void create(Joia j){
        repository.save(j);
    }
    public void delete(){}
    public void updated(){}
    public void getAll(){}
    public void getById(){}
}
