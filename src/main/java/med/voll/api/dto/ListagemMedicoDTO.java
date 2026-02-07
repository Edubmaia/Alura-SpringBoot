package med.voll.api.dto;

import med.voll.api.entities.Especialidade;
import med.voll.api.entities.Medico;

public record ListagemMedicoDTO(String nome, String email, String crm, Especialidade especialidade) {

	public ListagemMedicoDTO(Medico medico) {
		this(medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade());
	}
}
