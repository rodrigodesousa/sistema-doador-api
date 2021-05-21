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

import com.rodrigo.doador.DTO.PessoaDTO;
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
	public ResponseEntity<Void> inserirPessoa(@Valid @RequestBody PessoaDTO objDTO){
		Pessoa obj = pessoaService.converterDTO(objDTO);
		obj = pessoaService.inserirPessoa(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterarPessoa(@Valid @RequestBody PessoaDTO objDTO, @PathVariable Integer id){
		Pessoa obj = pessoaService.converterDTO(objDTO);
		obj.setId(id);
		obj = pessoaService.alterarPessoa(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletarPessoa(@PathVariable Integer id) {
		pessoaService.deletarPessoa(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<PessoaDTO>> listarPessoas(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Pessoa> list = pessoaService.listarPessoas(page, linesPerPage, orderBy, direction);
		Page<PessoaDTO> listDTO = list.map(obj -> new PessoaDTO(obj));
		
		return ResponseEntity.ok().body(listDTO);
	}
	
}
