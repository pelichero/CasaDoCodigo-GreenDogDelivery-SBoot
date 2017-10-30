package edu.fiap.estudos.casadocodigo.greendogdelivery.rest;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController 
public class NovoPedidoController {

	
	@Autowired
	public NovoPedidoController(ClienteRepository clienteRepository, ItemRepository itemRepository, EnviaNotificacao enviaNotificacao) {
		this.clienteRepository =clienteRepository;
		this.itemRepository=itemRepository;
		this.enviaNotificacao = enviaNotificacao;
	}

	private final ClienteRepository clienteRepository;
	private final ItemRepository itemRepository;
	private final EnviaNotificacao enviaNotificacao;

	@GetMapping("/rest/pedido/novo/{clienteId}/{listaDeItens}")
	public RespostaDTO novo(@PathVariable("clienteId") Long clienteId, @PathVariable("listaDeItens") String listaDeItens) {

		RespostaDTO dto = new RespostaDTO();

		try {
			Cliente c = clienteRepository.findOne(clienteId);

			String[] listaDeItensID = listaDeItens.split(",");

			Pedido pedido = new Pedido();

			double valorTotal = 0;
			List<Item> itensPedidos = new ArrayList<Item>();

			for (String itemId : listaDeItensID) {
				Item item = itemRepository.findOne(Long.parseLong(itemId));
				itensPedidos.add(item);
				valorTotal += item.getPreco();
			}
			pedido.setItens(itensPedidos);
			pedido.setValorTotal(valorTotal);
			pedido.setData(new Date());
			pedido.setCliente(c);
			c.getPedidos().add(pedido);

			this.clienteRepository.saveAndFlush(c);
			
			enviaNotificacao.enviaEmail(c,pedido);
			
			List<Long> pedidosID = new ArrayList<Long>();
			for (Pedido p : c.getPedidos()) {
				pedidosID.add(p.getId());
			}

			Long ultimoPedido = Collections.max(pedidosID);

			dto = new RespostaDTO(ultimoPedido,valorTotal,"Pedido efetuado com sucesso");

		} catch (Exception e) {
			dto.setMensagem("Erro: " + e.getMessage());
		}
		return dto;

	}

}