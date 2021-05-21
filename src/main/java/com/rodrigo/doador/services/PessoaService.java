package com.rodrigo.doador.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.doador.domain.Pessoa;
import com.rodrigo.doador.repositories.PessoaRepository;
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
}
