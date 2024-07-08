package com.joaogab.WebRadio.service;

import com.joaogab.WebRadio.model.AudioFile;
import com.joaogab.WebRadio.model.RadioStation;
import com.joaogab.WebRadio.repository.AudioFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class RadioService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://de1.api.radio-browser.info/json/stations/bycountryexact/brazil";

    public List<RadioStation> getBrazilianStations() {
        RadioStation[] stations = restTemplate.getForObject(API_URL, RadioStation[].class);
        return Arrays.asList(stations);
    }

    @Autowired
    private AudioFileRepository audioFileRepository;

    @Transactional
    public void saveAudioFile(MultipartFile file) throws Exception {
        AudioFile audioFile = new AudioFile();
        audioFile.setFileName(file.getOriginalFilename());
        audioFile.setData(file.getBytes());
        audioFileRepository.save(audioFile);
    }

    public AudioFile getLastUploadedAudioFile() {
        return audioFileRepository.findTopByOrderByIdDesc();
    }

}
