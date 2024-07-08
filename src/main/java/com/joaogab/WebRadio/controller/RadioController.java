package com.joaogab.WebRadio.controller;

import com.joaogab.WebRadio.model.AudioFile;
import com.joaogab.WebRadio.service.RadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RadioController {

    @Autowired
    private RadioService radioService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Bem-vindo à Web Radio!");
        model.addAttribute("stations", radioService.getBrazilianStations());
        return "index";
    }

    @GetMapping("/anuncios")
    public String anuncios(Model model) {
        AudioFile lastUploadedAudio = radioService.getLastUploadedAudioFile();
        model.addAttribute("audioUrl", "/api/audio-file-url");
        return "anuncios";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("message", "Página de Administração - Web Radio");
        return "admin";
    }


}
