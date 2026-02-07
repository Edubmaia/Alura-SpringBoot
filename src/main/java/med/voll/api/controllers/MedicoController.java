package med.voll.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.experimental.var;
import med.voll.api.Repository.MedicoRepository;
import med.voll.api.dto.AtualizarDadosMedicoDTO;
import med.voll.api.dto.DadosCadastroMedico;
import med.voll.api.dto.DetalhamentoMedicoDTO;
import med.voll.api.dto.ListagemMedicoDTO;
import med.voll.api.entities.Medico;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;
	
	@GetMapping
	public ResponseEntity<Page<ListagemMedicoDTO>> listar(Pageable pageable){
		 var page = repository.findAllByAtivoTrue(pageable).map(ListagemMedicoDTO::new);
	        return ResponseEntity.ok(page);	 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity findById(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		return ResponseEntity.ok(new DetalhamentoMedicoDTO(medico));
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(dados);
		repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DetalhamentoMedicoDTO(medico));

	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid AtualizarDadosMedicoDTO dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.AtualizarMedico(dados);
		return ResponseEntity.ok(new DetalhamentoMedicoDTO(medico));

	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable Long id) {
	var medico = repository.getReferenceById(id);
	medico.DeletarMedico();
	return ResponseEntity.noContent().build();
	
}
}