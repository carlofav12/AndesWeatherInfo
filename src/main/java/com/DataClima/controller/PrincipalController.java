package com.DataClima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DataClima.model.entity.Encuesta;
import com.DataClima.model.entity.Usuario;
import com.DataClima.service.IAdministradorService;
import com.DataClima.service.IEncuestaService;
import com.DataClima.service.IGaleriaService;
import com.DataClima.service.INoticiaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PrincipalController {
	@Autowired
	private INoticiaService noticiaService;
	@Autowired
	private IGaleriaService galeriaService;
	@Autowired
	private IAdministradorService administradorService;
	@Autowired
	private IEncuestaService encuestaService;

	@GetMapping("/AdministradorLogin")
	public String mostrarLogin(Model model) {
		return "/V_admin/Login";
	}

	@RequestMapping(value = "/AdministradorLogin", method = RequestMethod.POST)
	public String login(@RequestParam String nombre, @RequestParam String contra, RedirectAttributes redirAttrs,
			HttpSession session) {
		if (administradorService.validarAdmin(nombre, contra)) {
			System.out.println("¡Bienvenido al panel de administrador, " + nombre + "!");
			redirAttrs.addFlashAttribute("mensaje", "Bienvenido, " + nombre);
			session.setAttribute("nombre", nombre);
			return "redirect:/Administrador";
		} else {
			redirAttrs.addFlashAttribute("error", "Credenciales inválidas");
			return "redirect:/AdministradorLogin";
		}

	}

	@GetMapping("/inicioL")
	public String index(Model model, HttpSession session) {
		String nombreDepartamento = (String) session.getAttribute("nombreDepartamento");
		model.addAttribute("nombreDepartamento", nombreDepartamento);
		model.addAttribute("noticias", noticiaService.findNoticia());
		return "/V_usu/Inicio";
	}

	@GetMapping("/Blog")
	public String blog(Model model) {
		return "Blog";
	}

	// @GetMapping("/Galeria")
	// public String galeria(Model model) {
	// return "Galeria";
	// }

	@GetMapping("/NosotrosL")
	public String nosotros(Model model, HttpSession session) {
		String nombreDepartamento = (String) session.getAttribute("nombreDepartamento");
		model.addAttribute("nombreDepartamento", nombreDepartamento);
		return "/V_usu/Nosotros";
	}

	@GetMapping("/InformateL")
	public String info(Model model, HttpSession session) {
		String nombreDepartamento = (String) session.getAttribute("nombreDepartamento");
		model.addAttribute("nombreDepartamento", nombreDepartamento);
		return "/V_usu/Informate";
	}

	@GetMapping("/GaleriaL")
	public String galeria(Model model, HttpSession session) {
		String nombreDepartamento = (String) session.getAttribute("nombreDepartamento");
		model.addAttribute("nombreDepartamento", nombreDepartamento);
		model.addAttribute("galerias", galeriaService.findAllGaleria());
		return "/V_usu/Galeria";
	}

	@GetMapping("/encuesta")
	public String mostrarEncuesta(Model model, HttpSession session) {
		Object usuarioObj = session.getAttribute("usuario");
		if (usuarioObj instanceof Usuario) {
			Usuario usuario = (Usuario) usuarioObj;

			// Verificar si el usuario ya ha completado la encuesta
			boolean encuestaCompletada = encuestaService.existeEncuestaPorUsuario(usuario);

			if (encuestaCompletada) {
				model.addAttribute("mensajeEncuestaCompletada", "¡La encuesta ya ha sido completada!");
			} else {
				Encuesta encuesta = new Encuesta();
				encuesta.setUsuario(usuario);
				model.addAttribute("encuesta", encuesta);
			}
		} else {
			// si no se encuentra logeado
			return "redirect:/login";
		}
		return "/V_usu/Inicio";
	}

	@PostMapping("/guardarEncuesta")
	public String guardarEncuesta(@ModelAttribute("encuesta") Encuesta encuesta, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/V_usu/FormEncuesta";
		}

		encuestaService.save(encuesta);
		redirectAttributes.addFlashAttribute("mensaje", "Encuesta guardada exitosamente.");
		return "redirect:/inicioL";
	}
}