package edu.fiap.estudos.casadocodigo.greendogdelivery.config;

import edu.fiap.estudos.casadocodigo.greendogdelivery.dto.Notificacao;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
public class DevNotificacaoConfig implements Notificacao {

	@Override
	public boolean envioAtivo() {
		return false;
	}

}