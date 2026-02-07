package med.voll.api.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AtualizarDadosPacienteDTO;
import med.voll.api.dto.DadosCadastroPaciente;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String telefone;
	private String cpf;
	private Boolean ativo;

	@Embedded
	private Endereco endereco;

	public Paciente(DadosCadastroPaciente dados) {
		nome = dados.nome();
		email = dados.email();
		telefone = dados.telefone();
		cpf = dados.cpf();
		endereco = new Endereco(dados.endereco());
	}

	public void AtualizarPaciente(AtualizarDadosPacienteDTO dados) {
		if (dados.nome() != null) {
			nome = dados.nome();
		}
		if (dados.telefone() != null) {
			telefone = dados.telefone();
		}
		if (dados.endereco() != null) {
			endereco.AtualizarEndereco(dados.endereco());
		}
	}
	
	public void Deletar() {
		ativo = false;
	}
}
