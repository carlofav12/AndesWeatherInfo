package com.DataClima.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DataClima.model.entity.Publicacion;
import com.DataClima.model.entity.Usuario;
import com.DataClima.service.IPublicacionService;
import com.DataClima.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PublicacionController {

    @Autowired
    private IPublicacionService publicacionService;

    @Autowired
    private IUsuarioService usuarioService;


    @GetMapping("/BlogL")
    public String blog(Model model, HttpSession session) {
        // Añadir objeto 'publicacion' al modelo
        model.addAttribute("publicacion", new Publicacion());
        return "/V_usu/Blog";
    }

    @RequestMapping(value = "/BlogLista", method = RequestMethod.GET)
    public String lista(Model model) {
        List<Publicacion> publicaciones = publicacionService.findPublicacion();
        model.addAttribute("publicaciones", publicaciones);
        return "/V_usu/BlogList";
    }

    @RequestMapping(value = "/BlogL", method = RequestMethod.POST)
    public String guardar(@Valid Publicacion publicacion, BindingResult result, Model model,
                          @RequestParam("file") MultipartFile imagen, RedirectAttributes attribute, HttpSession session) {

        if (result.hasErrors()) {
            model.addAttribute("publicacion", publicacion);
            attribute.addFlashAttribute("warning", "Existieron errores en el Formulario");
            return "/V_usu/Blog";
        }

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main/resources//static/importar");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);

                publicacion.setImagen(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String nombreUsuario = (String) session.getAttribute("usuario");
        if (nombreUsuario == null) {
            attribute.addFlashAttribute("error", "Usuario no autenticado");
            return "redirect:/IniciarSesion";
        }

        Usuario usuario = usuarioService.findByUsuario(nombreUsuario);
        if (usuario == null) {
            attribute.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/IniciarSesion";
        }

        publicacion.setUsuario(usuario);
        publicacionService.savePublicacion(publicacion);

        attribute.addFlashAttribute("success", "Publicación guardada con éxito");
        return "redirect:/BlogLista";
    }
}