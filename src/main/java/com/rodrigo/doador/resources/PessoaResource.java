package com.rodrigo.doador.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rodrigo.doador.domain.Pessoa;
import com.rodrigo.doador.services.PessoaService;

@RestController
@RequestMapping(value="/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Pessoa> buscarPessoa(@PathVariable Integer id) {
		Pessoa obj = pessoaService.buscarPessoa(id);
		return ResponseEntity.ok().body(obj);
	}
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> inserirPessoa(@RequestBody Pessoa obj){
		obj = pessoaService.inserirPessoa(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterarPessoa(@RequestBody Pessoa obj, @PathVariable Integer id){
		obj.setId(id);
		obj = pessoaService.alterarPessoa(obj);
		return ResponseEntity.noContent().build();
	}
	
}
