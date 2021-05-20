package com.rodrigo.doador.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.doador.domain.Pessoa;
import com.rodrigo.doador.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa buscarPessoa(Integer id) {
		Optional<Pessoa> obj = pessoaRepository.findById(id);
		return obj.orElse(null);
	}
}
