package med.voll.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.DadosAutenticacao;
import med.voll.api.dto.DadosTokenJwtDTO;
import med.voll.api.entities.Usuario;
import med.voll.api.services.TokenService;

@RestController
@RequestMapping("login")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;


	@PostMapping
	    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
	        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
	        var authentication = manager.authenticate(authenticationToken);
	        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
	        return ResponseEntity.ok(new DadosTokenJwtDTO(tokenJWT));
	    }
}
