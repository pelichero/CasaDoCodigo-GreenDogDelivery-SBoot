package edu.fiap.estudos.casadocodigo.greendogdelivery.repository;

import edu.fiap.estudos.casadocodigo.greendogdelivery.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}