package med.voll.api.dto;

import med.voll.api.entities.Endereco;
import med.voll.api.entities.Especialidade;
import med.voll.api.entities.Medico;

public record DetalhamentoMedicoDTO(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DetalhamentoMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
	