package com.rodrigo.doador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigo.doador.domain.Utensilio;

@Repository
public interface UtensilioRepository extends JpaRepository<Utensilio, Integer> {

}
