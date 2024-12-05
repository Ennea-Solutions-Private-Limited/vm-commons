package com.ennea.enneaservices.utils.reminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BroadcastServiceFactory {

    private final DefaultBroadcastParserServiceImpl defaultReminderParserService;

    private final DistributorPromotionsBroadcastParserServiceImpl distributorBroadcastParserService;

    @Autowired
    public BroadcastServiceFactory(DefaultBroadcastParserServiceImpl defaultBroadcastParserService, DistributorPromotionsBroadcastParserServiceImpl distributorBroadcastParserService) {
        this.defaultReminderParserService = defaultBroadcastParserService;
        this.distributorBroadcastParserService = distributorBroadcastParserService;
    }

    public BroadcastParserService getBroadcastParserService(String parser) {
        if (parser.equals("default_distributor_ad")) {
            return distributorBroadcastParserService;
        } else {
            return defaultReminderParserService;
        }
    }
}
