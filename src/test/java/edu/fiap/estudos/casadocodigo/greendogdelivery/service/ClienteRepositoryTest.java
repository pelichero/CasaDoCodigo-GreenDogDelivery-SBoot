package edu.fiap.estudos.casadocodigo.greendogdelivery.service;

import edu.fiap.estudos.casadocodigo.greendogdelivery.model.Cliente;
import edu.fiap.estudos.casadocodigo.greendogdelivery.repository.ClienteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteRepositoryTest {

	@Autowired
	ClienteRepository repository;

	@Test
	public void buscaClientesCadastrados() {

		Page<Cliente> clientes = this.repository.findAll(new PageRequest(0, 10));
		assertThat(clientes.getTotalElements()).isGreaterThan(1L);
	}

	@Test
	public void buscaClienteFernando() {
		
		Cliente clienteNaoEncontrado = this.repository.findByNome("Fernando");
		assertThat(clienteNaoEncontrado).isNull();

		Cliente cliente = this.repository.findByNome("Fernando Boaglio");
		assertThat(cliente).isNotNull();
		assertThat(cliente.getNome()).isEqualTo("Fernando Boaglio");
		assertThat(cliente.getEndereco()).isEqualTo("Sampa");
		
	}
 
}