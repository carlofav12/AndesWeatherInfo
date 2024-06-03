package com.DataClima.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.Persona;
import com.DataClima.model.entity.Publicacion;
import com.DataClima.model.entity.Usuario;
import com.DataClima.service.IDepartamentoService;
import com.DataClima.service.IGaleriaService;
import com.DataClima.service.INoticiaService;
import com.DataClima.service.IPersonaService;
import com.DataClima.service.IPublicacionService;
import com.DataClima.service.IUsuarioService;


@Controller
public class UsuarioController {
	@Autowired
    private IDepartamentoService departamentoService;
    @Autowired
    private IPersonaService personaService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IPublicacionService publicacionService;
    @Autowired
    private IGaleriaService galeriaService;
	@Autowired
	private INoticiaService noticiaService;
    
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("noticias", noticiaService.findNoticia());
		List<Departamento> departamentos = departamentoService.findAll();
        int randomIndex = (int) (Math.random() * departamentos.size());
        Departamento departamentoAleatorio = departamentos.get(randomIndex);
        model.addAttribute("nombreDepartamento", departamentoAleatorio.getNombre());
		return "Inicio";
	}
    
    @GetMapping("/IniciarSesion")
    public String iniciarSesion(Model model) {
        return "IniciarSesion";
    }

    @GetMapping("/Registrarse")
    public String mostrarFormularioDeRegistro(Model model) {
        List<Departamento> departamentos = departamentoService.getAllDepartamentos();
        model.addAttribute("departamentos", departamentos);
        return "Registrarse";
    }

    @PostMapping("/Registrarse")
    public String registrar(@RequestParam String nombre, @RequestParam String apellido_paterno,
                            @RequestParam String apellido_materno, @RequestParam String genero,
                            @RequestParam String tipo_documento, @RequestParam String numero_documento,
                            @RequestParam String correo, @RequestParam String departamento,
                            @RequestParam String usuario, @RequestParam String contrasena,
                            @RequestParam(required = false) Long usuarioId,
                            @RequestParam(required = false) Long personaId, Model model) {

        Departamento dep = departamentoService.findByNombre(departamento);
        
        Persona persona;
        if (personaId != null) {
            persona = personaService.findById(personaId);
            persona.setNombre(nombre);
            persona.setApePa(apellido_paterno);
            persona.setApeMa(apellido_materno);
            persona.setGenero(genero);
            persona.setTipDoc(tipo_documento);
            persona.setNumDoc(numero_documento);
            persona.setCorreo(correo);
            persona.setDepartamento(dep);
        } else {
            persona = new Persona();
            persona.setNombre(nombre);
            persona.setApePa(apellido_paterno);
            persona.setApeMa(apellido_materno);
            persona.setGenero(genero);
            persona.setTipDoc(tipo_documento);
            persona.setNumDoc(numero_documento);
            persona.setCorreo(correo);
            persona.setDepartamento(dep);
        }
        personaService.savePersona(persona);

        Usuario newUsuario;
        if (usuarioId != null) {
            newUsuario = usuarioService.findById(usuarioId);
            newUsuario.setUsu(usuario);
            newUsuario.setContra(contrasena);
            newUsuario.setPersona(persona);
        } else {
            newUsuario = new Usuario();
            newUsuario.setUsu(usuario);
            newUsuario.setContra(contrasena);
            newUsuario.setPersona(persona);
        }
        usuarioService.saveUsuario(newUsuario);

        return "redirect:/IniciarSesion";
    }

    @GetMapping("/Registrarse/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable Long id, Model model) {
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
            
            // Verificar si el departamento es nulo antes de obtener su nombre
            if (usuario.getPersona().getDepartamento() != null) {
                model.addAttribute("departamento", usuario.getPersona().getDepartamento().getNombre());
            } else {
                model.addAttribute("departamento", ""); // O un valor por defecto
            }
            
            model.addAttribute("usuario", usuario.getUsu());
            model.addAttribute("contrasena", usuario.getContra());
        } else {
            // Manejo de caso en el que el usuario no se encuentra
            return "redirect:/listarClientes";
        }

        List<Departamento> departamentos = departamentoService.getAllDepartamentos();
        model.addAttribute("departamentos", departamentos);
        return "Registrarse";
    }

	@GetMapping("/Nosotros")
	public String nosotros(Model model) {
		return "Nosotros";
	}
	
	@GetMapping("/Galeria")
	public String galeria(Model model) {
		model.addAttribute("galerias", galeriaService.findAllGaleria());
		return "/Galeria";
	}
	

	
}
