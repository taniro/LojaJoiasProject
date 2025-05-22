package ufrn.br.lojajoiasproject;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ufrn.br.lojajoiasproject.domain.Usuario;
import ufrn.br.lojajoiasproject.service.FileStorageService;
import ufrn.br.lojajoiasproject.service.UsuarioService;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class LojaJoiasProjectApplication implements CommandLineRunner, WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(LojaJoiasProjectApplication.class, args);
    }

    @Resource
    FileStorageService storageService;

    @Autowired
    UsuarioService service;

    @Override
    public void run(String... args) throws Exception {

        var criptografa = new BCryptPasswordEncoder();

        storageService.deleteAll();
        storageService.init();

        Usuario u1 = new Usuario("user", criptografa.encode("user"), false);
        Usuario u2 = new Usuario("admin", criptografa.encode("admin"), true);

        service.create(u1);
        service.create(u2);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Register resource handler for images
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
    }
}
