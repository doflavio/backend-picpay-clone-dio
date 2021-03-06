package io.github.doflavio.picpayclone.resource;

import javax.validation.Valid;

import io.github.doflavio.picpayclone.service.impl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.doflavio.picpayclone.dto.LoginDTO;
import io.github.doflavio.picpayclone.dto.TokenDTO;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoResource extends ResourceBase<TokenDTO> {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginDTO loginDTO) {
		UsernamePasswordAuthenticationToken dadosLogin = loginDTO.converter();

		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return responderSucessoComItem(new TokenDTO(token, "Bearer"));
		} catch (AuthenticationException e) {
			return responderRequisicaoMalSucedida();
		}
	}

}
