package ufrn.br.lojajoiasproject.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ufrn.br.lojajoiasproject.domain.Usuario;
import ufrn.br.lojajoiasproject.repository.UsuarioRepository;

import java.util.Optional;


@Service
public class UsuarioService implements UserDetailsService {

    UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void create(Usuario u){
        repository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optional = repository.findUsuarioByUsername(username);
        if(optional.isPresent()) {
            return optional.get();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
