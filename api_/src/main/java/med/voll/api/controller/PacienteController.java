package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.paciente.DadosAtualizacaoPacientes;
import med.voll.api.paciente.DadosCadastroPacientes;
import med.voll.api.paciente.DadosListagemPacientes;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacientesRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
	
	@Autowired
	private PacientesRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody DadosCadastroPacientes dados) {
		Paciente paciente = new Paciente(dados);
		repository.save(paciente);
		System.out.println("Paciente cadastrado com sucesso.");
	}
	
	@GetMapping
	public List<DadosListagemPacientes> listar(Pageable paginacao){
		System.out.println("Listando pacientes");
		return repository.findAll().stream().map(DadosListagemPacientes::new).toList();
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody DadosAtualizacaoPacientes dados) {
		var paciente = repository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
		System.out.println("Paciente atualizado com sucesso.");
		
	}

}
