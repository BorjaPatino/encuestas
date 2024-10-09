package com.iesjuanbosco.encuestas.controller;

import com.iesjuanbosco.encuestas.entity.Encuesta;
import com.iesjuanbosco.encuestas.repository.EncuestaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class EncuestaController {

    private EncuestaRepository encuestaRepository;

    @Autowired
    public EncuestaController(EncuestaRepository encuestaRepository) {
        this.encuestaRepository = encuestaRepository;
    }



    @GetMapping("/encuestas/new")
    public String newEncuesta(Model model) {
        model.addAttribute("encuesta", new Encuesta());
        return "encuesta-new";
    }

    @PostMapping("/encuestas/new")
    public String insertEncuesta(@Valid @ModelAttribute Encuesta encuesta, BindingResult result) {
        if (result.hasErrors()) {
            return "encuesta-new";
        }
        encuestaRepository.save(encuesta);
        return "redirect:/encuestas";
    }

    @GetMapping("/encuestas/delete/{id}")
    public String deleteEncuesta(@PathVariable Long id) {
        encuestaRepository.deleteById(id);
        return "redirect:/encuestas";
    }

    @GetMapping("/encuestas/edit/{id}")
    public String editEncuesta(@PathVariable Long id, Model model) {
        Optional<Encuesta> encuesta = encuestaRepository.findById(id);
        if (encuesta.isPresent()) {
            model.addAttribute("encuesta", encuesta.get());
            return "encuesta-edit";
        }
        return "redirect:/encuestas";
    }

    @PostMapping("/encuestas/edit/{id}")
    public String updateEncuesta(@PathVariable Long id, @Valid @ModelAttribute Encuesta encuesta, BindingResult result) {
        if (result.hasErrors()) {
            return "encuesta-edit"; // Asegúrate de que "encuesta-edit" sea el nombre correcto de la vista
        }
        encuesta.setId(id); // Asegúrate de que el ID sea correcto
        encuestaRepository.save(encuesta); // Guarda la encuesta actualizada
        return "redirect:/encuestas"; // Redirige a la lista de encuestas
    }

    @GetMapping("/encuestas/view/{id}")
    public String viewEncuesta(@PathVariable Long id, Model model) {
        Optional<Encuesta> encuesta = encuestaRepository.findById(id);
        if (encuesta.isPresent()) {
            model.addAttribute("encuesta", encuesta.get());
            return "encuesta-view";
        }
        return "redirect:/encuestas";
    }

    @GetMapping("/encuestas")
    public String findAll(@RequestParam(required = false) String filtro,
                          @RequestParam(required = false) String nivelSatisfaccion,
                          Model model) {
        List<Encuesta> encuestas = encuestaRepository.findAll();

        // Filtrar por nombre
        if (filtro != null && !filtro.isEmpty()) {
            encuestas = encuestas.stream()
                    .filter(encuesta -> encuesta.getNombre().toLowerCase().contains(filtro.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Filtrar por nivel de satisfacción
        if (nivelSatisfaccion != null && !nivelSatisfaccion.isEmpty()) {
            encuestas = encuestas.stream()
                    .filter(encuesta -> nivelSatisfaccion.equals(encuesta.getNivelSatisfaccion()))
                    .collect(Collectors.toList());
        }

        // Calcular estadísticas
        long totalEncuestas = encuestas.size();
        double promedioEdad = encuestas.stream().mapToInt(Encuesta::getEdad).average().orElse(0);

        // Distribución de niveles de satisfacción
        long muySatisfechos = encuestas.stream().filter(encuesta -> "Muy satisfecho".equals(encuesta.getNivelSatisfaccion())).count();
        long satisfechos = encuestas.stream().filter(encuesta -> "Satisfecho".equals(encuesta.getNivelSatisfaccion())).count();
        long insatisfechos = encuestas.stream().filter(encuesta -> "Insatisfecho".equals(encuesta.getNivelSatisfaccion())).count();

        double porcentajeMuySatisfechos = totalEncuestas > 0 ? (double) muySatisfechos / totalEncuestas * 100 : 0;
        double porcentajeSatisfechos = totalEncuestas > 0 ? (double) satisfechos / totalEncuestas * 100 : 0;
        double porcentajeInsatisfechos = totalEncuestas > 0 ? (double) insatisfechos / totalEncuestas * 100 : 0;

        // Añadir datos al modelo
        model.addAttribute("encuestas", encuestas);
        model.addAttribute("totalEncuestas", totalEncuestas);
        model.addAttribute("promedioEdad", promedioEdad);
        model.addAttribute("porcentajeMuySatisfechos", porcentajeMuySatisfechos);
        model.addAttribute("porcentajeSatisfechos", porcentajeSatisfechos);
        model.addAttribute("porcentajeInsatisfechos", porcentajeInsatisfechos);

        return "encuesta-list";
    }






}