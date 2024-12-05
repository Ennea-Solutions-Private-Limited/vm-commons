package com.ennea.enneaservices.utils.reminder;

import com.ennea.enneaservices.model.Broadcast;
import com.ennea.enneaservices.model.BroadcastTemplate;

import java.util.Map;

public interface BroadcastParserService {
    Broadcast getBroadcast(BroadcastTemplate template, Map<String, String> oldRow);
}
