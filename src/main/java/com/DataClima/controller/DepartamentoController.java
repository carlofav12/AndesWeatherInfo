package com.DataClima.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.DataClima.model.entity.Departamento;
import com.DataClima.model.entity.DepartamentoFenomeno;
import com.DataClima.model.entity.Ecoregion;
import com.DataClima.model.entity.Enf_dep_fen;
import com.DataClima.model.entity.Enfermedades;
import com.DataClima.model.entity.Fenomeno_cli;
import com.DataClima.model.entity.Prevencion_Enf;
import com.DataClima.model.entity.Prevenciones;
import com.DataClima.model.entity.Provincia;
import com.DataClima.model.entity.TemporadaDepartamento;
import com.DataClima.service.IDepFenService;
import com.DataClima.service.IDepartamentoService;
import com.DataClima.service.IEcorregionService;
import com.DataClima.service.IEnfDepFenService;
import com.DataClima.service.IFenomenoCliService;
import com.DataClima.service.IPrevencionEnfService;
import com.DataClima.service.IPrevencionesService;
import com.DataClima.service.IProvinciaService;
import com.DataClima.service.ITemporadaDepartamentoService;

import jakarta.servlet.http.HttpSession;
@Controller
public class DepartamentoController {

    @Autowired
    private IDepartamentoService departamentoService;
    @Autowired
    private IProvinciaService provinciaService;
    @Autowired
    private IDepFenService DepFenService;
    @Autowired
    private IFenomenoCliService fenomenoCliService;
    @Autowired
    private IPrevencionesService prevencionesService;
    @Autowired
    private IEnfDepFenService enfDepFenService;
    @Autowired
    private IPrevencionEnfService prevencionEnfService;
	@Autowired
	private IEcorregionService ecorregionService;
    @Autowired
	private ITemporadaDepartamentoService temporadaDepartamentoService;

    @GetMapping("/Departamentos")
	public String dep(Model model) {
		List<Departamento> departamentos = departamentoService.getAllDepartamentos(); // Obtén los departamentos desde el servicio
		List<Ecoregion> ecoregiones = ecorregionService.getAllEcoregiones(); // Obtén las ecoregiones desde el servicio

		// Filtrar ecoregiones que tienen departamentos asociados
        List<Ecoregion> ecoregionesConDepartamentos = ecoregiones.stream()
            .filter(ecoregion -> departamentos.stream()
                .anyMatch(departamento -> departamento.getEcoregion().getCodEcoRegion().equals(ecoregion.getCodEcoRegion())))
            .collect(Collectors.toList());

		model.addAttribute("departamentos", departamentos); // Agrega los departamentos al modelo
        model.addAttribute("ecoregiones", ecoregionesConDepartamentos);
		return "Departamentos"; // Devuelve el nombre de la plantilla Thymeleaf
	}

	@GetMapping("/departamentos")
	public String listarDepartamentos(Model model) {
		List<Departamento> departamentos = departamentoService.findAll();
		model.addAttribute("departamentos", departamentos);
		return "listaDepartamentos";
	}

	@GetMapping("/DepartamentosL")
	public String depL(Model model,HttpSession session) {
		List<Departamento> departamentos = departamentoService.getAllDepartamentos(); // Obtén los departamentos desde el servicio
		List<Ecoregion> ecoregiones = ecorregionService.getAllEcoregiones(); // Obtén las ecoregiones desde el servicio

		// Filtrar ecoregiones que tienen departamentos asociados
        List<Ecoregion> ecoregionesConDepartamentos = ecoregiones.stream()
            .filter(ecoregion -> departamentos.stream()
                .anyMatch(departamento -> departamento.getEcoregion().getCodEcoRegion().equals(ecoregion.getCodEcoRegion())))
            .collect(Collectors.toList());

		model.addAttribute("departamentos", departamentos); // Agrega los departamentos al modelo
        model.addAttribute("ecoregiones", ecoregionesConDepartamentos);
		return "/V_usu/Departamentos"; // Devuelve el nombre de la plantilla Thymeleaf
	}

	@GetMapping("/departamentosL")
	public String listarDepartamentosL(Model model, HttpSession session) {
		List<Departamento> departamentos = departamentoService.findAll();
		model.addAttribute("departamentos", departamentos);
		String nombreDepartamento = (String) session.getAttribute("nombreDepartamento");
		model.addAttribute("nombreDepartamento", nombreDepartamento);
		return "listaDepartamentos";
	}

    @GetMapping("/{nombre}")
    public String mostrarDepartamento(@PathVariable String nombre, Model model) {
        Departamento departamento = departamentoService.findByNombre(nombre.toUpperCase());
        if (departamento != null) {
            model.addAttribute("departamento", departamento);
            List<Provincia> provincias = provinciaService.findByDepartamento(departamento);
            model.addAttribute("provincias", provincias);
            List<DepartamentoFenomeno> fenomenos = DepFenService.findByDepartamento(departamento);
            model.addAttribute("fenomenos", fenomenos);
            List<TemporadaDepartamento> temporadas = temporadaDepartamentoService.findByDepartamento(departamento);
        model.addAttribute("temporadas", temporadas);
            return "infoDep";
        } else {
            return "error";
        }
    }

    @GetMapping("/fenomeno/{departamentoId}/{fenomenoId}")
    public String mostrarFenomeno(@PathVariable Long departamentoId, @PathVariable Long fenomenoId, Model model) {
        Optional<Fenomeno_cli> fenomenoOpt = fenomenoCliService.findById(fenomenoId);
        Departamento departamento = departamentoService.findById(departamentoId);

        if (fenomenoOpt.isPresent() && departamento != null) {
            Fenomeno_cli fenomeno = fenomenoOpt.get();
            model.addAttribute("fenomeno", fenomeno);
            model.addAttribute("departamento", departamento);

            List<DepartamentoFenomeno> departamentoFenomenos = DepFenService.findByFenomenoAndDepartamento(fenomeno,
                    departamento);

            for (DepartamentoFenomeno depFen : departamentoFenomenos) {
                List<Prevenciones> prevenciones = prevencionesService.findByDepartamentoFenomeno(depFen);
                depFen.setPrevenciones(prevenciones);

                // Busca las enfermedades relacionadas a travÃ©s de Enf_dep_fen
                List<Enf_dep_fen> enfDepFenList = enfDepFenService.findByDepartamentoFenomeno(depFen);
                List<Enfermedades> enfermedades = new ArrayList<>();
                for (Enf_dep_fen enfDepFen : enfDepFenList) {
                    Enfermedades enfermedad = enfDepFen.getEnfermedad();
                    enfermedades.add(enfermedad);
                    List<Prevencion_Enf> prevencionesEnf = prevencionEnfService.findByEnfermedad(enfermedad);
                enfermedad.setPrevencionEnfList(prevencionesEnf);
                }
            }

            model.addAttribute("departamentoFenomenos", departamentoFenomenos);

            return "feno";
        } else {
            return "error";
        }
    }

    @GetMapping("/departamentoL/{nombre}")
	public String mostrarDepartamentoL(@PathVariable String nombre, Model model, HttpSession session) {
		String nombreDepartamento = (String) session.getAttribute("nombreDepartamento");
		model.addAttribute("nombreDepartamento", nombreDepartamento);
		Departamento departamento = departamentoService.findByNombre(nombre.toUpperCase());
		if (departamento != null) {
			model.addAttribute("departamento", departamento);
			List<Provincia> provincias = provinciaService.findByDepartamento(departamento);
			model.addAttribute("provincias", provincias);
			List<DepartamentoFenomeno> fenomenos = DepFenService.findByDepartamento(departamento);
			model.addAttribute("fenomenos", fenomenos);
			return "/V_usu/infoDep";
		} else {
			return "error";
		}
	}

	@GetMapping("/fenomenoL/{departamentoId}/{fenomenoId}")
	public String mostrarFenomenoL(@PathVariable Long departamentoId, @PathVariable Long fenomenoId, Model model,
			HttpSession session) {
		String nombreDepartamento = (String) session.getAttribute("nombreDepartamento");
		model.addAttribute("nombreDepartamento", nombreDepartamento);
		Optional<Fenomeno_cli> fenomenoOpt = fenomenoCliService.findById(fenomenoId);
		Departamento departamento = departamentoService.findById(departamentoId);

		if (fenomenoOpt.isPresent() && departamento != null) {
			Fenomeno_cli fenomeno = fenomenoOpt.get();
			model.addAttribute("fenomeno", fenomeno);
			model.addAttribute("departamento", departamento);

			List<DepartamentoFenomeno> departamentoFenomenos = DepFenService.findByFenomenoAndDepartamento(fenomeno,
					departamento);

			for (DepartamentoFenomeno depFen : departamentoFenomenos) {
				List<Prevenciones> prevenciones = prevencionesService.findByDepartamentoFenomeno(depFen);
				depFen.setPrevenciones(prevenciones);

				// Busca las enfermedades relacionadas a travÃ©s de Enf_dep_fen
				List<Enf_dep_fen> enfDepFenList = enfDepFenService.findByDepartamentoFenomeno(depFen);
				List<Enfermedades> enfermedades = new ArrayList<>();
				for (Enf_dep_fen enfDepFen : enfDepFenList) {
					Enfermedades enfermedad = enfDepFen.getEnfermedad();
					enfermedades.add(enfermedad);
					List<Prevencion_Enf> prevencionesEnf = prevencionEnfService.findByEnfermedad(enfermedad);
					enfermedad.setPrevencionEnfList(prevencionesEnf);
				}
			}

			model.addAttribute("departamentoFenomenos", departamentoFenomenos);

			return "/V_usu/feno";
		} else {
			return "error";
		}
	}

}