package com.rodrigo.doador.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigo.doador.domain.Utensilio;
import com.rodrigo.doador.services.UtensilioService;

@RestController
@RequestMapping(value="/utensilios")
public class UtensilioResource {

	@Autowired
	private UtensilioService utensilioService;
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Utensilio> buscarUtensilio(@PathVariable Integer id) {
		Utensilio obj = utensilioService.buscarUtensilio(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserirUtensilio(@Valid @RequestBody Utensilio obj){
		obj = utensilioService.inserirUtensilio(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterarUtensilio(@Valid @RequestBody Utensilio obj, @PathVariable Integer id){
		obj.setId(id);
		obj = utensilioService.alterarUtensilio(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletarUtensilio(@PathVariable Integer id) {
		utensilioService.deletarUtensilio(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Utensilio>> listarUtensilios(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Utensilio> list = utensilioService.listarUtensilios(page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.ok().body(list);
	}
	
}
