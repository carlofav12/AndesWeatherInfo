package com.DataClima.controller;

import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DataClima.model.entity.Administrador;
import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.Galeria;
import com.DataClima.model.entity.Noticia;
import com.DataClima.model.entity.Usuario;
import com.DataClima.service.IAdministradorService;
import com.DataClima.service.IDepartamentoService;
import com.DataClima.service.IGaleriaService;
import com.DataClima.service.INoticiaService;
import com.DataClima.service.IPersonaService;
import com.DataClima.service.IPublicacionService;
import com.DataClima.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/Administrador")
public class AdministradorController {

	@Autowired
	private IDepartamentoService departamentoService;
	@Autowired
	private IPersonaService personaService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IPublicacionService publicacionService;
	@Autowired
	private IAdministradorService administradorService;

	@RequestMapping(method = RequestMethod.GET)
	public String inicio(Model model) {
		return "/V_admin/Inicio";
	}



	@RequestMapping(value = "/lista-usuarios", method = RequestMethod.GET)
	public String lista(Model model) {
		model.addAttribute("usuarios", usuarioService.findUsuario());
		return "/V_admin/ListaUsuarios";
	}

	@RequestMapping(value = "/eliminar-usuario/{id}", method = RequestMethod.GET)
	public String eliminar(@PathVariable("id") Long id) {
		Usuario usuario = usuarioService.findById(id);
		if (usuario != null) {
			publicacionService.eliminarPublicacionesPorUsuario(usuario);
			personaService.eliminarPersona(usuario.getPersona().getId());

			usuarioService.eliminarUsuario(id);
		}

		return "redirect:/Administrador/lista-usuarios";
	}

	@GetMapping("/modificar-usuario/{id}")
	public String modificar(@PathVariable Long id, Model model) {
		Usuario usuario = usuarioService.findById(id);
		if (usuario != null) {
			model.addAttribute("usuarioId", usuario.getId());
			model.addAttribute("personaId", usuario.getPersona().getId());
			model.addAttribute("nombre", usuario.getPersona().getNombre());
			model.addAttribute("apellido_paterno", usuario.getPersona().getApePa());
			model.addAttribute("apellido_materno", usuario.getPersona().getApeMa());
			model.addAttribute("genero", usuario.getPersona().getGenero());
			model.addAttribute("tipo_documento", usuario.getPersona().getTipDoc());
			model.addAttribute("numero_documento", usuario.getPersona().getNumDoc());
			model.addAttribute("correo", usuario.getPersona().getCorreo());

			if (usuario.getPersona().getDepartamento() != null) {
				model.addAttribute("departamento", usuario.getPersona().getDepartamento().getNombre());
			} else {
				model.addAttribute("departamento", "");
			}

			model.addAttribute("usuario", usuario.getUsu());
			model.addAttribute("contrasena", usuario.getContra());
		} else {
			return "redirect:/Administrador/lista-usuarios";
		}

		List<Departamento> departamentos = departamentoService.getAllDepartamentos();
		model.addAttribute("departamentos", departamentos);
		return "/V_admin/ModificarUsuario";
	}

	@RequestMapping(value = "/modificar-usuario/{id}", method = RequestMethod.POST)
	public String actualizarUsuario(
			@PathVariable Long id,
			@RequestParam("nombre") String nombre,
			@RequestParam("apellido_paterno") String apellidoPaterno,
			@RequestParam("apellido_materno") String apellidoMaterno,
			@RequestParam("genero") String genero,
			@RequestParam("tipo_documento") String tipoDocumento,
			@RequestParam("numero_documento") String numeroDocumento,
			@RequestParam("correo") String correo,
			@RequestParam("departamento") String departamentoNombre,
			@RequestParam("usuario") String usuarioNombre,
			@RequestParam("contrasena") String contrasena,
			RedirectAttributes redirectAttributes) {

		Usuario usuario = usuarioService.findById(id);
		if (usuario != null) {
			usuario.getPersona().setNombre(nombre);
			usuario.getPersona().setApePa(apellidoPaterno);
			usuario.getPersona().setApeMa(apellidoMaterno);
			usuario.getPersona().setGenero(genero);
			usuario.getPersona().setTipDoc(tipoDocumento);
			usuario.getPersona().setNumDoc(numeroDocumento);
			usuario.getPersona().setCorreo(correo);

			Departamento departamento = departamentoService.findByNombre(departamentoNombre);
			usuario.getPersona().setDepartamento(departamento);

			usuario.setUsu(usuarioNombre);
			usuario.setContra(contrasena);

			usuarioService.saveUsuario(usuario);
			redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado correctamente");
		}

		return "redirect:/Administrador/lista-usuarios";
	}

}