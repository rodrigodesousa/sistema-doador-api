package com.rodrigo.doador.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.doador.DTO.PessoaDTO;
import com.rodrigo.doador.domain.Pessoa;
import com.rodrigo.doador.repositories.PessoaRepository;
import com.rodrigo.doador.services.exceptions.DataIntegrityException;
import com.rodrigo.doador.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa buscarPessoa(Integer id) {
		Optional<Pessoa> obj = pessoaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id + " Tipo: " + Pessoa.class.getName()));
	}
	public Pessoa inserirPessoa(Pessoa obj) {
		obj.setId(null);
		return pessoaRepository.save(obj);
	}
	public Pessoa alterarPessoa(Pessoa obj) {
		buscarPessoa(obj.getId());
		return pessoaRepository.save(obj);
	}
	public void deletarPessoa(Integer id) {
		buscarPessoa(id);
		try {
			pessoaRepository.deleteById(id);			
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Pessoa que possui utensilios");
		}
	}
	public List<Pessoa> listarTodasPessoas(){
		return pessoaRepository.findAll();
	}
	public Page<Pessoa> listarPessoas(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return pessoaRepository.findAll(pageRequest);
	}
	public Pessoa converterDTO(PessoaDTO objDTO) {
		return new Pessoa(objDTO.getId(), objDTO.getNome(), objDTO.getEmail());
	}
}
