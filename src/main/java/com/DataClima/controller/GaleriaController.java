package com.DataClima.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DataClima.model.entity.Galeria;
import com.DataClima.service.IGaleriaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Administrador/Galeria")
public class GaleriaController {

    @Autowired
    private IGaleriaService galeriaService;

    @RequestMapping(method = RequestMethod.GET)
    public String lista(Model model) {
        model.addAttribute("galerias", galeriaService.findAllGaleria());
        return "/V_admin/ListaGaleria";
    }

    @RequestMapping(value = "/AgregarGaleria", method = RequestMethod.GET)
    public String agregar(Model model) {
        model.addAttribute("galeria", new Galeria());
        return "/V_admin/RegistroGaleria";
    }

    @RequestMapping(value = "/AgregarGaleria", method = RequestMethod.POST)
    public String guardarGaleria(@Valid Galeria galeria, BindingResult result, Model model,
                                 @RequestParam("file") MultipartFile archivo, RedirectAttributes attribute) {

        if (result.hasErrors()) {
            model.addAttribute("galeria", galeria);
            attribute.addFlashAttribute("warning", "Existieron errores en el Formulario");
            return "/V_admin/RegistroGaleria";
        }

        if (!archivo.isEmpty()) {
            Path directorioArchivos = Paths.get("src/main/resources/static/images/galeria");
            String rutaAbsoluta = directorioArchivos.toFile().getAbsolutePath();

            try {
                byte[] bytesArchivo = archivo.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + archivo.getOriginalFilename());
                Files.write(rutaCompleta, bytesArchivo);
                galeria.setEnlace(archivo.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        galeriaService.saveGaleria(galeria);
        attribute.addFlashAttribute("success", "Galería guardada con éxito");
        return "redirect:/Administrador/Galeria";
    }

    @RequestMapping(value = "/ModificarGaleria/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
        Galeria galeria = null;

        if (id > 0) {
            galeria = galeriaService.editarGaleria(id);
        } else {
            return "redirect:/Administrador/Galeria";
        }
        model.put("galeria", galeria);
        return "/V_admin/RegistroGaleria";
    }

    @RequestMapping(value = "/ModificarGaleria", method = RequestMethod.POST)
    public String actualizarGaleria(@Valid Galeria galeria, BindingResult result, Model model,
                                    @RequestParam("file") MultipartFile archivo, RedirectAttributes attribute) {

        if (result.hasErrors()) {
            model.addAttribute("galeria", galeria);
            attribute.addFlashAttribute("warning", "Existieron errores en el Formulario");
            return "/V_admin/RegistroGaleria";
        }

        if (!archivo.isEmpty()) {
            Path directorioArchivos = Paths.get("src/main/resources/static/images/galeria");
            String rutaAbsoluta = directorioArchivos.toFile().getAbsolutePath();

            try {
                byte[] bytesArchivo = archivo.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + archivo.getOriginalFilename());
                Files.write(rutaCompleta, bytesArchivo);
                galeria.setEnlace(archivo.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Galeria galeriaExistente = galeriaService.editarGaleria(galeria.getId());
            if (galeriaExistente != null) {
                galeria.setEnlace(galeriaExistente.getEnlace());
            }
        }

        galeriaService.saveGaleria(galeria);
        attribute.addFlashAttribute("success", "Galería actualizada con éxito");
        return "redirect:/Administrador/Galeria";
    }
    
    @RequestMapping(value = "/EliminarGaleria/{id}", method = RequestMethod.GET)
    public String eliminar2(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            galeriaService.eliminarGaleria(id);
        }
        return "redirect:/Administrador/Galeria";
    }
}