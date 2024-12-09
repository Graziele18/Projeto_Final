package ifrn.pf.projeto.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifrn.pf.projeto.models.Convidado;
import ifrn.pf.projeto.models.Projeto;
import ifrn.pf.projeto.repositories.ConvidadoRepository;
import ifrn.pf.projeto.repositories.ProjetoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {

	@Autowired
	private ProjetoRepository pr;

	@Autowired
	private ConvidadoRepository cr;

	@GetMapping("/form")
	public String form(Projeto projeto) {
		return "projeto/formProjeto";
	}

	@PostMapping
	public String adicionar(@Valid Projeto projeto, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return form(projeto);
		}

		System.out.println(projeto);
		pr.save(projeto);

		attributes.addFlashAttribute("mensagem", "Salvo com sucesso!");

		return "redirect:/projeto";
	}

	@GetMapping
	public ModelAndView listar() {

		List<Projeto> projeto = pr.findAll();
		ModelAndView mv = new ModelAndView("projeto/lista");
		mv.addObject("projeto", projeto);
		return mv;

	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id, Convidado convidado) {
		ModelAndView md = new ModelAndView("");
		Optional<Projeto> opt = pr.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/projeto");
			return md;

		}
		md.setViewName("projeto/detalhes");
		Projeto projeto = opt.get();
		md.addObject("projeto", projeto);

		List<Convidado> convidados = cr.findByProjeto(projeto);
		md.addObject("convidados", convidados);

		return md;
	}
	
	@PostMapping("/{idProjeto}")
	public String salvarConvidado(@PathVariable Long idProjeto, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagemErro", "Verifique se todos os campos estão preenchidos!");
			return "redirect:/projeto/{idProjeto}";
		}
		
		System.out.println("Id do evento: " + idProjeto);
		System.out.println(convidado);
		
		Optional<Projeto> opt = pr.findById(idProjeto);
		if (opt.isEmpty()) {
			return "redirect:/projeto";
		}
		
		
		Projeto projeto = opt.get();
		convidado.setProjeto(projeto);
		
		cr.save(convidado);
		
		attributes.addFlashAttribute("mensagem", "Salvo com sucesso!");
		
		return "redirect:/projeto/{idProjeto}";
		
	}
	
	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarProjeto(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Projeto> opt = pr.findById(id);
		if(opt.isEmpty()) {
			md.setViewName("redirect:/projeto");
			return md;
		}
		
		Projeto projeto = opt.get();
		md.setViewName("projeto/formProjeto");
		md.addObject("projeto", projeto);
		
		return md;
		
	}
	
	@GetMapping("/{idProjeto}/convidados/{idConvidado}/selecionar") 
	public ModelAndView selecionarConvidado(@PathVariable Long idProjeto, @PathVariable Long idConvidado) { 
		ModelAndView md = new ModelAndView();
		
		Optional<Projeto> optProjeto = pr.findById(idProjeto);
		Optional<Convidado> optConvidado = cr.findById(idConvidado);
		
		if(optProjeto.isEmpty() || optConvidado.isEmpty()) {
			md.setViewName("redirect:/projeto");
			return md;
		}
		
		Projeto projeto = optProjeto.get();
		Convidado convidado = optConvidado.get();
		
		if(projeto.getId() != convidado.getProjeto().getId()) {
			md.setViewName("redirect:/projeto");
			return md;
		}
		
		md.setViewName("projeto/detalhes");
		md.addObject("convidado", convidado);
		md.addObject("projeto", projeto);
		md.addObject("convidados", cr.findByProjeto(projeto));
		
		return md;
		
	}
	
	@GetMapping("/{id}/remover")
	public String apagarProjeto(@PathVariable Long id, RedirectAttributes attributes) {
		
		Optional<Projeto> opt = pr.findById(id);
		
		if(!opt.isEmpty()) {
			Projeto projeto = opt.get();
			
			List<Convidado> convidados = cr.findByProjeto(projeto);
			
			cr.deleteAll(convidados);
			pr.delete(projeto);
			attributes.addFlashAttribute("mensagem", "Removido com sucesso!");
			
		}
		
		return "redirect:/projeto";
		
	}
	
	@GetMapping("/detalhes/{id}/remover")
	public String apagarConvidado(@PathVariable Long id) {
		
		Optional<Convidado> optConvidado = cr.findById(id);
		
		if(optConvidado.isPresent()) {
			
			Convidado convidado = optConvidado.get();
			
			cr.delete(convidado);
			
		}
		
		return "redirect:/projeto";
		
	}

}