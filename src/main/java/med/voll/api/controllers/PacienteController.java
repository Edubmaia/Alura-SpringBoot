package med.voll.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.Repository.PacienteRepository;
import med.voll.api.dto.AtualizarDadosPacienteDTO;
import med.voll.api.dto.DadosCadastroPaciente;
import med.voll.api.dto.DetalhamentoPacienteDTO;
import med.voll.api.dto.ListagemPacienteDTO;
import med.voll.api.entities.Paciente;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
	
	
	@Autowired
	private PacienteRepository repository;
	
	@GetMapping("{/id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		return ResponseEntity.ok(new DetalhamentoPacienteDTO(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<ListagemPacienteDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		var page = repository.findByAtivoTrue(paginacao).map(ListagemPacienteDTO::new);
		return ResponseEntity.ok(page);
	}
	
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder ) {
		var paciente = new Paciente(dados);
		repository.save(paciente);
		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).body(new DetalhamentoPacienteDTO(paciente));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid AtualizarDadosPacienteDTO dados) {
		var paciente = repository.getReferenceById(dados.id());
		paciente.AtualizarPaciente(dados);
		return ResponseEntity.ok(new DetalhamentoPacienteDTO(paciente));
		
	}
	
	@DeleteMapping("{/id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		paciente.Deletar();
		return ResponseEntity.noContent().build();
	}

}
