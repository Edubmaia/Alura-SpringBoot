package med.voll.api.dto;

import med.voll.api.entities.Paciente;

public record ListagemPacienteDTO(String nome, String email, String cpf) {

	public ListagemPacienteDTO(Paciente paciente) {
		this(paciente.getNome(),paciente.getEmail(),paciente.getCpf());
	}
}
