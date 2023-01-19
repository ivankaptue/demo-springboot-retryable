package com.klid.demospringbootretryable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Ivan Kaptue
 */
@Service
public class RetryableService {

    private static final Logger logger = LoggerFactory.getLogger(RetryableService.class);

    private final NewService newService;

    public RetryableService(NewService newService) {
        this.newService = newService;
    }

    public void handleAction(boolean retry) {
        subAction1();
        newService.subAction2(retry);
    }

    private void subAction1() {
        logger.info("subAction1");
    }
}
