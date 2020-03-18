package edu.eci.arsw.drawit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface SalaRepository extends JpaRepository<Sala, Integer> {

}
