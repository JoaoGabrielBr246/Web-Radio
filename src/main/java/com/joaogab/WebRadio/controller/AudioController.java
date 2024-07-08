package com.joaogab.WebRadio.controller;

import com.joaogab.WebRadio.model.AudioFile;
import com.joaogab.WebRadio.service.RadioService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/api")
public class AudioController {

    @Autowired
    private RadioService radioService;

    @PostMapping("/upload")
    public String uploadAudioFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "redirect:/admin?error=Arquivo vazio";
            }
            radioService.saveAudioFile(file);
            return "redirect:/admin?success";
        } catch (IOException e) {
            return "redirect:/admin?error=Falha ao processar o arquivo";
        } catch (Exception e) {
            return "redirect:/admin?error=Erro interno no servidor";
        }
    }


    @GetMapping("/audio-file-url")
    @ResponseBody
    public void getAudioFile(HttpServletResponse response) throws IOException {
        AudioFile audioFile = radioService.getLastUploadedAudioFile();
        if (audioFile != null) {
            response.setContentType("audio/mpeg");
            response.setHeader("Content-Disposition", "inline; filename=\"" + audioFile.getFileName() + "\"");
            OutputStream out = response.getOutputStream();
            out.write(audioFile.getData());
            out.flush();
            out.close();
        }
    }
}
