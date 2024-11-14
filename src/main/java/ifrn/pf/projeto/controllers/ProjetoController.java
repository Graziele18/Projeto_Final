package ifrn.pf.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pf.projeto.models.Projeto;
import ifrn.pf.projeto.repositories.ProjetoRepository;

@Controller
public class ProjetoController {
	
	@Autowired
	private ProjetoRepository pr;
	
	@RequestMapping("/projeto/form")
	public String form() {
		return "projeto/formProjeto";
	}
	
	@RequestMapping("/projeto")
	public String adicionar(Projeto projeto) {
		
		System.out.println(projeto);
		pr.save(projeto);
		
		return "projeto/projeto-adicionado";
	}

}
