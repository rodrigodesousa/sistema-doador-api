package com.rodrigo.doador.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rodrigo.doador.domain.Utensilio;

@Repository
public interface UtensilioRepository extends JpaRepository<Utensilio, Integer> {

	@Query("SELECT DISTINCT obj from Utensilio obj WHERE obj.disponivel = TRUE")
	Page<Utensilio> listarUtensiliosDisponiveis(Pageable pagerequest);
}
