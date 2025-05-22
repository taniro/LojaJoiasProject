package ufrn.br.lojajoiasproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.lojajoiasproject.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByUsername(String username);
}
