package med.voll.api.dto;

import med.voll.api.entities.Endereco;
import med.voll.api.entities.Paciente;

public record DetalhamentoPacienteDTO(Long id, String nome, String email, String telefone,String cpf , Endereco endereco) {

    public DetalhamentoPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
	