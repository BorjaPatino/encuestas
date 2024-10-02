package com.iesjuanbosco.encuestas.controller;

import com.iesjuanbosco.encuestas.entity.Encuesta;
import com.iesjuanbosco.encuestas.repository.EncuestaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EncuestaController {

    private EncuestaRepository encuestaRepository;

    @Autowired
    public EncuestaController(EncuestaRepository encuestaRepository) {
        this.encuestaRepository = encuestaRepository;
    }

    @GetMapping("/encuestas")
    public String findAll(Model model) {
        List<Encuesta> encuestas = encuestaRepository.findAll();
        model.addAttribute("encuestas", encuestas);
        return "encuesta-list";
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
            return "encuesta-edit";
        }
        encuesta.setId(id);
        encuestaRepository.save(encuesta);
        return "redirect:/encuestas";
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
}
