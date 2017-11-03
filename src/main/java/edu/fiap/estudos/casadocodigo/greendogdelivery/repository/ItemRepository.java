package edu.fiap.estudos.casadocodigo.greendogdelivery.repository;

import edu.fiap.estudos.casadocodigo.greendogdelivery.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel="itens",path="itens")
public interface ItemRepository extends JpaRepository<Item, Long> {

}