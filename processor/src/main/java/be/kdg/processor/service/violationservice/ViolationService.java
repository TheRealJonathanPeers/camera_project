package be.kdg.processor.service.violationservice;

import be.kdg.processor.models.messages.CameraMessage;
import be.kdg.processor.models.violations.Violation;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;


//TODO: query schrijven om berichten op te halen van de laatste 30 minuten bij snelheid, tov vorige dag bij emissie

public interface ViolationService {
    Violation checkViolation(CameraMessage cameraMessage, Collection<CameraMessage> cameraMessages) throws IOException;
}