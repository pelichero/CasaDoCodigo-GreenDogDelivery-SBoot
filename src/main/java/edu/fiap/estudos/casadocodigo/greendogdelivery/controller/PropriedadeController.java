package edu.fiap.estudos.casadocodigo.greendogdelivery.controller;

import edu.fiap.estudos.casadocodigo.greendogdelivery.model.Propriedade;
import edu.fiap.estudos.casadocodigo.greendogdelivery.repository.PropriedadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PropriedadeController {

    @Autowired
    private PropriedadeRepository repo;

    @RequestMapping(value="/find/{filtro}", method = RequestMethod.GET)
    List<Propriedade> findByFiltro(@PathVariable("filtro") String filtro){
        return repo.findByFiltro(filtro);
    }
}