package br.com.desafio.behoh.api.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.behoh.api.web.domain.Usuario;
import br.com.desafio.behoh.api.web.service.UsuarioService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public ResponseEntity<?> listarTodosOsUsuarios() {
		return ResponseEntity.ok(usuarioService.listarUsuarios());
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> salvarUsuario(@RequestBody @Validated Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> buscarUsuarioPeloId(@PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.buscarUsarioPeloId(id));
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deletarUsuarioPeloId(@PathVariable Long id) {
		usuarioService.deletarUsuarioPeloId(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/usuarios")
	public ResponseEntity<?> editarUsuario(@RequestBody @Validated Usuario usuario) throws NotFoundException {
		return ResponseEntity.ok(usuarioService.atualizarUsuario(usuario));
	}
}