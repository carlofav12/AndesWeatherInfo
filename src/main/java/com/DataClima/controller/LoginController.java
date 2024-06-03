package com.DataClima.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DataClima.model.entity.Usuario;
import com.DataClima.service.IUsuarioService;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private IUsuarioService usuService;

	private static final String adminUser = "c";
	private static final String adminPass = "c";

	private static final Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/IniciarSesion", method = RequestMethod.POST)
	public String login(@RequestParam String usuario, @RequestParam String contrasena, HttpSession session,
			RedirectAttributes redirAttrs) {
		if (usuario.equalsIgnoreCase(adminUser) && contrasena.equalsIgnoreCase(adminPass)) {
			return "redirect:/Administrador";
		}

		// Buscar el usuario en la base de datos para obtener el nombre del departamento
		Usuario usuarioRegistrado = usuService.findByUsuario(usuario);
		if (usuarioRegistrado != null && usuarioRegistrado.getPersona() != null) {
			String nombreDepartamento = usuarioRegistrado.getPersona().getDepartamento().getNombre();
			session.setAttribute("usuario", usuarioRegistrado);
			session.setAttribute("nombreDepartamento", nombreDepartamento); // Guardar el nombre del departamento en la
																			// sesión
			logger.info("Inicio de sesión exitoso para usuario: {}", usuario);
			return "redirect:/inicioL";
		} else {
			logger.warn("Fallo de inicio de sesión para usuario: {}", usuario);
			redirAttrs.addFlashAttribute("error", "Usuario o contraseña incorrectos");
			return "redirect:/IniciarSesion";
		}
	}

	@GetMapping("/CerrarSesion")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/IniciarSesion";
	}

}
