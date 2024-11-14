package ifrn.pf.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.pf.projeto.models.Projeto;
import ifrn.pf.projeto.repositories.ProjetoRepository;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {
	
	@Autowired
	private ProjetoRepository pr;
	
	@GetMapping("/form")
	public String form() {
		return "projeto/formProjeto";
	}
	
	@PostMapping
	public String adicionar(Projeto projeto) {
		
		System.out.println(projeto);
		pr.save(projeto);
		
		return "projeto/projeto-adicionado";
	}
	
	@GetMapping
	public ModelAndView listar() {
		
		List<Projeto> projeto = pr.findAll();
		ModelAndView mv = new ModelAndView("projeto/lista");
		mv.addObject("projeto", projeto);
		return mv;
		
	}

}
