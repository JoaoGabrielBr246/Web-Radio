package com.joaogab.WebRadio.repository;

import com.joaogab.WebRadio.model.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioFileRepository extends JpaRepository<AudioFile, Long> {
    AudioFile findTopByOrderByIdDesc();
}
