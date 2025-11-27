/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.logging;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinayak
 */
public class EventLogger implements GameEventListener {
    private final List<GameEvent> eventLog = new ArrayList<>();

    @Override
    public void updateOnGameEvent(GameEvent event) {
        eventLog.add(event);
    }

    public void LogEventsToCSV() {
        CSVLogger csvLogger = new CSVLogger("src/main/java/com/intellectual_systems/resources/game_events_log.csv");
        csvLogger.logGameEvents(this.eventLog);
    }



}
