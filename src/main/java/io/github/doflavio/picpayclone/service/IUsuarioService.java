package io.github.doflavio.picpayclone.service;

import java.util.List;

import io.github.doflavio.picpayclone.dto.UsuarioDTO;
import io.github.doflavio.picpayclone.modelo.Transacao;
import io.github.doflavio.picpayclone.modelo.Usuario;

public interface IUsuarioService {

	Usuario consultarEntidade(String login);
	
	UsuarioDTO consultar(String login);

	void atualizarSaldo(Transacao transacaoSalva, Boolean isCartaoCredito);

	void validar(Usuario... usuarios);

	List<UsuarioDTO> listar(String login);

}
