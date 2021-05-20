package com.rodrigo.doador.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigo.doador.domain.Pessoa;
import com.rodrigo.doador.services.PessoaService;

@RestController
@RequestMapping(value="/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarPessoa(@PathVariable Integer id) {
		Pessoa obj = pessoaService.buscarPessoa(id);
		return ResponseEntity.ok().body(obj);
	}
}
