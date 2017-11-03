package edu.fiap.estudos.casadocodigo.greendogdelivery.controller;

import edu.fiap.estudos.casadocodigo.greendogdelivery.dto.Notificacao;
import edu.fiap.estudos.casadocodigo.greendogdelivery.dto.RespostaDTO;
import edu.fiap.estudos.casadocodigo.greendogdelivery.model.Cliente;
import edu.fiap.estudos.casadocodigo.greendogdelivery.model.Item;
import edu.fiap.estudos.casadocodigo.greendogdelivery.model.Pedido;
import edu.fiap.estudos.casadocodigo.greendogdelivery.repository.ClienteRepository;
import edu.fiap.estudos.casadocodigo.greendogdelivery.repository.ItemRepository;
import edu.fiap.estudos.casadocodigo.greendogdelivery.util.EnviaNotificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NovoPedidoController {

	private final ItemRepository itemRepository;
	private final ClienteRepository clienteRepository;

	@Autowired
	private EnviaNotificacao enviaNotificacao;

	@Autowired
	public NovoPedidoController(ClienteRepository clienteRepository, ItemRepository itemRepository) {
		this.clienteRepository = clienteRepository;
		this.itemRepository = itemRepository;
	}

	@GetMapping("/rest/pedido/novo/{clienteId}/{listaDeItens}")
	public RespostaDTO novo(@PathVariable("clienteId") Long clienteId, @PathVariable("listaDeItens") String listaDeItens) {

		RespostaDTO dto = new RespostaDTO();
		try {
			Cliente c = clienteRepository.findOne(clienteId);

			List<Item> itensPedidos = Arrays.stream(listaDeItens.split(",")).map(id -> itemRepository.findOne(Long.parseLong(id))).collect(Collectors.toList());

			c.getPedidos().add(
					new Pedido(null
							, c
							, itensPedidos
							, itensPedidos.stream().mapToDouble(Item::getPreco).sum()
							, new Date()));

			this.clienteRepository.saveAndFlush(c);
			enviaNotificacao.enviaEmail(c,c.getPedidos().get(0));
			dto = new RespostaDTO(c.getPedidos().stream().mapToLong(Pedido::getId).max().getAsLong(), c.getPedidos().get(0).getValorTotal(), "Pedido efetuado com sucesso");
		} catch (Exception e) {
			dto.setMensagem("Erro: " + e.getMessage());
		}
		return dto;
	}
}