package ufrn.br.lojajoiasproject.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ufrn.br.lojajoiasproject.domain.Joia;
import ufrn.br.lojajoiasproject.service.JoiaService;

@Controller
public class JoiaController {

    JoiaService service;

    public JoiaController(JoiaService service) {
        this.service = service;
    }

    @PostMapping("/doProcessSave")
    public String doProcessSave(@ModelAttribute Joia j){
        service.create(j);
        return "redirect:/cadastro";
    }

    @GetMapping("/cadastro" )
    public String getCadatroPage(Model model){

        Joia j = new Joia();
        model.addAttribute("joia", j);

        return "cadastro";
    }
}
