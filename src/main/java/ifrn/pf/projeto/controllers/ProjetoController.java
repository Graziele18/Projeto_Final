package ifrn.pf.projeto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pf.projeto.models.Projeto;

@Controller
public class ProjetoController {
	
	@RequestMapping("/projeto/form")
	public String form() {
		return "formProjeto";
	}
	
	@RequestMapping("/projeto")
	public String adicionar(Projeto projeto) {
		
		System.out.println(projeto);
		
		return "projeto-adicionado";
	}

}
