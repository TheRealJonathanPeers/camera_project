package be.kdg.simulator.messaging.messengers;


import be.kdg.simulator.models.CameraMessage;

public interface Messenger {
    void sendMessage(CameraMessage message);
}
