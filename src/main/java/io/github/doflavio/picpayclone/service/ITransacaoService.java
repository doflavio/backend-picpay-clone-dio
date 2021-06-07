package io.github.doflavio.picpayclone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.github.doflavio.picpayclone.dto.TransacaoDTO;

public interface ITransacaoService {

	TransacaoDTO processar(TransacaoDTO transacaoDTO);

	Page<TransacaoDTO> listar(Pageable paginacao, String usuario);

}
