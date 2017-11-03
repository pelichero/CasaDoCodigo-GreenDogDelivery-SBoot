package edu.fiap.estudos.casadocodigo.greendogdelivery.util;

import edu.fiap.estudos.casadocodigo.greendogdelivery.dto.Notificacao;
import edu.fiap.estudos.casadocodigo.greendogdelivery.model.Cliente;
import edu.fiap.estudos.casadocodigo.greendogdelivery.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnviaNotificacao {

    @Autowired
	Notificacao notificacao;
    
	public void enviaEmail(Cliente cliente, Pedido pedido) {
		
		System.out.println("Enviar notificacao para "+cliente.getNome() + " - pedido $"+pedido.getValorTotal());
		
		if (notificacao.envioAtivo()) {
			System.out.println("Notificacao enviada!");
		} else {
			System.out.println("Notificacao desligada!");
		
		}
	}
	
	
}