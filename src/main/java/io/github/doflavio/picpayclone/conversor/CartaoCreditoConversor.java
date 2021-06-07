package io.github.doflavio.picpayclone.conversor;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.doflavio.picpayclone.dto.CartaoCreditoDTO;
import io.github.doflavio.picpayclone.dto.TransacaoDTO;
import io.github.doflavio.picpayclone.modelo.CartaoCredito;
import io.github.doflavio.picpayclone.modelo.Transacao;
import io.github.doflavio.picpayclone.service.IUsuarioService;
import io.github.doflavio.picpayclone.utils.CartaoCreditoUtil;

@Component
public class CartaoCreditoConversor extends ConversorBase<CartaoCredito, CartaoCreditoDTO> {

	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public CartaoCreditoDTO converterEntidadeParaDto(CartaoCredito entidade) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Transacao, TransacaoDTO>() {
			@Override
			protected void configure() {
			}
		});
		return modelMapper.map(entidade, CartaoCreditoDTO.class);
	}

	@Override
	public CartaoCredito converterDtoParaEntidade(CartaoCreditoDTO dto) {
		return CartaoCredito
				.builder()
				.bandeira(dto.getBandeira())
				.numero(CartaoCreditoUtil.mascarar(dto.getNumero()))
				.numeroToken(dto.getNumeroToken())
				.usuario(usuarioService.consultarEntidade(dto.getUsuario().getLogin()))
				.build();
	}

}
