package ufrn.br.lojajoiasproject.service;

import org.springframework.stereotype.Service;
import ufrn.br.lojajoiasproject.domain.Joia;
import ufrn.br.lojajoiasproject.repository.JoiaRepository;

import java.util.List;

@Service
public class JoiaService {

    JoiaRepository repository;

    public JoiaService(JoiaRepository repository) {
        this.repository = repository;
    }

    public  void create(Joia j){
        repository.save(j);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
    public void updated(){}
    public List<Joia> getAll(){
        return repository.findAll();
    }
    public Joia getById(Long id){
        return repository.findById(id).get();
    }
}
