package com.DataClima.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.DataClima.model.entity.Noticia;
import com.DataClima.service.INoticiaService;

import org.springframework.ui.Model;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Administrador/Noticias")
public class NoticiaController {

    @Autowired
    private INoticiaService noticiaService;

    @RequestMapping(method = RequestMethod.GET)
    public String lista3(Model model) {
        model.addAttribute("noticias", noticiaService.findNoticia());
        return "/V_admin/ListaNoticias";
    }

    @RequestMapping(value = "/AgregarNoticia", method = RequestMethod.GET)
    public String lista4(Model model) {
        model.addAttribute("noticia", new Noticia());
        return "/V_admin/RegistroNoticia";
    }

    @RequestMapping(value = "/AgregarNoticia", method = RequestMethod.POST)
    public String guardarNoticia(@Valid Noticia noticia, BindingResult result, Model model,
                                 @RequestParam("file") MultipartFile archivo, RedirectAttributes attribute) {

        if (result.hasErrors()) {
            model.addAttribute("noticia", noticia);
            attribute.addFlashAttribute("warning", "Existieron errores en el Formulario");
            return "NoticiaLista";
        }

        if (!archivo.isEmpty()) {
            Path directorioArchivos = Paths.get("src/main/resources/static/images/noticias");
            String rutaAbsoluta = directorioArchivos.toFile().getAbsolutePath();

            try {
                byte[] bytesArchivo = archivo.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + archivo.getOriginalFilename());
                Files.write(rutaCompleta, bytesArchivo);
                noticia.setImgNoti(archivo.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        noticiaService.saveNoticia(noticia);
        attribute.addFlashAttribute("success", "Noticia guardada con éxito");
        return "redirect:/Administrador/Noticias";
    }

    @RequestMapping(value = "/ModificarNoticia/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Noticia noticia = null;

        if (id > 0) {
            noticia = noticiaService.editarNoti(id);
        } else {
            return "redirect:/Administrador/Noticias";
        }
        model.put("noticia", noticia);
        return "/V_admin/RegistroNoticia";
    }

    @RequestMapping(value = "/ModificarNoticia", method = RequestMethod.POST)
    public String actualizarNoticia(@Valid Noticia noticia, BindingResult result, Model model,
                                    @RequestParam("file") MultipartFile archivo, RedirectAttributes attribute) {

        if (result.hasErrors()) {
            model.addAttribute("noticia", noticia);
            attribute.addFlashAttribute("warning", "Existieron errores en el Formulario");
            return "NoticiaLista";
        }

        if (!archivo.isEmpty()) {
            Path directorioArchivos = Paths.get("src/main/resources/static/images/noticias");
            String rutaAbsoluta = directorioArchivos.toFile().getAbsolutePath();

            try {
                byte[] bytesArchivo = archivo.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + archivo.getOriginalFilename());
                Files.write(rutaCompleta, bytesArchivo);
                noticia.setImgNoti(archivo.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Noticia noticiaExistente = noticiaService.editarNoti(noticia.getId());
            if (noticiaExistente != null) {
                noticia.setImgNoti(noticiaExistente.getImgNoti());
            }
        }

        noticiaService.saveNoticia(noticia);
        attribute.addFlashAttribute("success", "Noticia actualizada con éxito");
        return "redirect:/Administrador/Noticias";
    }

    @RequestMapping(value = "/EliminarNoticia/{id}", method = RequestMethod.GET)
    public String eliminar2(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            noticiaService.eliminarNoti(id);
        }
        return "redirect:/Administrador/Noticias";
    }
}