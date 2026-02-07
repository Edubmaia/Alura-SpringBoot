package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarDadosMedicoDTO(
		@NotNull
		Long id,
		
		String nome,
		String telefone,
		DadosEndereco endereco) {

}
