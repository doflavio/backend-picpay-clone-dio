package io.github.doflavio.picpayclone.service.impl;

import io.github.doflavio.picpayclone.conversor.CartaoCreditoConversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import io.github.doflavio.picpayclone.dto.CartaoCreditoDTO;
import io.github.doflavio.picpayclone.modelo.CartaoCredito;
import io.github.doflavio.picpayclone.repository.CartaoCreditoRepository;
import io.github.doflavio.picpayclone.service.ICartaoCreditoService;
import io.github.doflavio.picpayclone.service.IUsuarioService;

@Service
public class CartaoCreditoService implements ICartaoCreditoService {

	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;

	@Autowired
	private CartaoCreditoConversor cartaoCreditoConversor;
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public CartaoCreditoDTO salvar(CartaoCreditoDTO cartaoCreditoDTO) {
		CartaoCreditoDTO cartaoCreditoRetorno = null;
		if (cartaoCreditoDTO.getIsSalva()) {
			CartaoCredito cartaoCredito = cartaoCreditoConversor.converterDtoParaEntidade(cartaoCreditoDTO);
			usuarioService.validar(cartaoCredito.getUsuario());
			CartaoCredito cartaoCreditoSalvo = cartaoCreditoRepository.save(cartaoCredito);
			cartaoCreditoRetorno = cartaoCreditoConversor.converterEntidadeParaDto(cartaoCreditoSalvo);
		}

		return cartaoCreditoRetorno;
	}

}
