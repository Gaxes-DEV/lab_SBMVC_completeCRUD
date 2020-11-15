package com.cenfotec.crud.controller;

import com.cenfotec.crud.domain.Article;
import com.cenfotec.crud.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cenfotec.crud.domain.Antology;
import com.cenfotec.crud.service.AntologyService;

import java.util.Optional;
import java.util.Set;

@Controller
public class AntologyController {

	@Autowired
	AntologyService anthologyService;
	@Autowired
	ArticleService articleService;

	private Optional<Antology> getAnthology(Long id){
		Optional<Antology> antologyFound = anthologyService.get(id);
		return antologyFound;
	}
	
	@RequestMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.GET)
	public String insertarPage(Model model) {
		model.addAttribute(new Antology());
		return "insertar";
	}	
	
	@RequestMapping(value = "/insertar",  method = RequestMethod.POST)
	public String insertarAction(Antology antology, BindingResult result, Model model) {
		anthologyService.save(antology);
		return "index";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("anthologies",anthologyService.getAll());
		return "listar";
	}

	@RequestMapping(value = "/listar/antologia/{id}")
	public String antologiaPerfil(@PathVariable("id") Long id, Model model) throws ParseException, java.text.ParseException{
		Optional<Antology> listA = getAnthology(id);

		model.addAttribute("key", listA.get().getId());
		model.addAttribute("title", listA.get().getName());
		model.addAttribute("subTitle", listA.get().getSubTitle());
		model.addAttribute("publishedDate", listA.get().getPublishedDate());
		model.addAttribute("articles", listA.get().getArticles());
		return "perfilAntologia";
	}

	@RequestMapping("/listar/antologiaM/{id}")
	public String antologiaModificar(@PathVariable("id") Long id, Model model) throws ParseException, java.text.ParseException{
		Optional<Antology> listA = getAnthology(id);

		model.addAttribute("antology", listA);
		model.addAttribute("key", listA.get().getId());
		model.addAttribute("title", listA.get().getName());
		model.addAttribute("subTitle", listA.get().getSubTitle());
		model.addAttribute("publishedDate", listA.get().getPublishedDate());

		return "insertar";
	}

	@RequestMapping("/listar/antologiaD/{id}")
	public String antologiaEliminar(@PathVariable("id") Long id, Model model) throws ParseException, java.text.ParseException{
		anthologyService.deleteAntology(id);
		model.addAttribute("anthologies",anthologyService.getAll());
		return "listar";
	}

	//-----------------------------------------------------------------------------------------

	@RequestMapping(value = "/insertarArticulo/{id}",  method = RequestMethod.GET)
	public String insertarArticuloVista(Model model, @PathVariable String id) {
		model.addAttribute(new Article());
		return "insertarArticulo";
	}

	@RequestMapping(value = "/insertarArticulo/{id}",  method = RequestMethod.POST)
	public String insertarArticuloAction(@PathVariable("id") Long id, Article article, BindingResult result, Model model)
			throws ParseException, java.text.ParseException{
		Antology anthology = getAnthology(id).get();
		article.setAnthology(anthology);

		articleService.save(article);

		return "index";
	}
}
