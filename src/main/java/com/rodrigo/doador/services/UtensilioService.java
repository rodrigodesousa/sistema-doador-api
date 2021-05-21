package com.rodrigo.doador.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.doador.domain.Utensilio;
import com.rodrigo.doador.repositories.UtensilioRepository;
import com.rodrigo.doador.services.exceptions.DataIntegrityException;
import com.rodrigo.doador.services.exceptions.ObjectNotFoundException;

@Service
public class UtensilioService {
	
	@Autowired
	private UtensilioRepository utensilioRepository;
	
	@Autowired
	private PessoaService pessoaService;

	public Utensilio buscarUtensilio(Integer id) {
		Optional<Utensilio> obj = utensilioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado ID: " + id + " Tipo: " + Utensilio.class.getName()));
	}
	public Utensilio inserirUtensilio(Utensilio obj) {
		obj.setId(null);
		obj.setPessoa(pessoaService.buscarPessoa(obj.getPessoa().getId()));
		return utensilioRepository.save(obj);
	}
	public Utensilio alterarUtensilio(Utensilio obj) {
		buscarUtensilio(obj.getId());
		return utensilioRepository.save(obj);
	}
	public void deletarUtensilio(Integer id) {
		buscarUtensilio(id);
		try {
			utensilioRepository.deleteById(id);			
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Utensilio que possui utensilios");
		}
	}
	public Page<Utensilio> listarUtensilios(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return utensilioRepository.findAll(pageRequest);
	}
	public Page<Utensilio> listarUtensiliosDisponiveis(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return utensilioRepository.listarUtensiliosDisponiveis(pageRequest);
	}
}
