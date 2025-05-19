package ufrn.br.lojajoiasproject.controler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ufrn.br.lojajoiasproject.domain.Joia;
import ufrn.br.lojajoiasproject.service.FileStorageService;
import ufrn.br.lojajoiasproject.service.JoiaService;

@Controller
public class JoiaController {

    JoiaService service;
    FileStorageService fileStorageService;

    public JoiaController(JoiaService service, FileStorageService fileStorageService) {
        this.service = service;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/")
    public String getHomePage(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        session.setAttribute("msg", "Hello World");
        model.addAttribute("msg", session.getAttribute("msg"));
        model.addAttribute("joiaList", service.getAll());
        return "index";
    }

    @RequestMapping(value = "/doProcessSaveWithFile", method = RequestMethod.POST)
    public String doSalvar(@ModelAttribute @Valid Joia j, Errors errors, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        if (errors.hasErrors()){
            return "cadastro";
        }else{

            j.setImagemUri(file.getOriginalFilename());
            service.create(j);
            fileStorageService.save(file);
            redirectAttributes.addAttribute("msg", "Cadastro realizado com sucesso");
            return "redirect:/";
        }
    }

    @PostMapping("/doProcessSave")
    public String doProcessSave(@ModelAttribute @Valid Joia j, Errors errors){
        if (errors.hasErrors()){
            return "cadastro";
        }
        service.create(j);
        return "redirect:/";
    }

    @GetMapping("/cadastro" )
    public String getCadatroPage(Model model){

        Joia j = new Joia();
        model.addAttribute("joia", j);

        return "cadastro";
    }

    @GetMapping("/deletar/{id}")
    public String doProcessDelete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String getEdicaoPage(Model model, @PathVariable Long id){
        model.addAttribute("joia", service.getById(id));
        return "edicao";
    }
}
