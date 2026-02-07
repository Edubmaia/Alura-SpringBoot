package med.voll.api.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AtualizarDadosMedicoDTO;
import med.voll.api.dto.DadosCadastroMedico;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;
	private String telefone;
	private String crm;
	
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;
	
	private Boolean ativo;
	
	public Medico(DadosCadastroMedico dados) {
		nome = dados.nome();
		email = dados.email();
		crm = dados.crm();
		telefone = dados.telefone();
		especialidade = dados.especialidade();
		endereco = new Endereco(dados.endereco());
	}
	
	public void AtualizarMedico(AtualizarDadosMedicoDTO dados) {
		if (dados.nome() != null) {
			nome = dados.nome();
		}
		if (dados.telefone() != null) {
			telefone= dados.telefone();
		}
		if (dados.endereco() != null) {
			endereco.AtualizarEndereco(dados.endereco());
		}
	}
	
	public void DeletarMedico() {
	this.ativo = false;
	}
}
