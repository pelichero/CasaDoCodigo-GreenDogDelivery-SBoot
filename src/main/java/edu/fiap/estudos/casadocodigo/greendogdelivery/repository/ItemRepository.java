package edu.fiap.estudos.casadocodigo.greendogdelivery.repository;

import edu.fiap.estudos.casadocodigo.greendogdelivery.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}