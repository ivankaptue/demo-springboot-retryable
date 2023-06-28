package com.klid.demospringbootretryable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author Ivan Kaptue
 */
@Service
public class NewService {

    private static final Logger logger = LoggerFactory.getLogger(NewService.class);

    @Retryable(
            retryFor = RetryableException.class,
            maxAttemptsExpression = "#{${app.process.max-attempts}}",
            backoff = @Backoff(delayExpression = "#{${app.process.backoff}}")
    )
    public void subAction2(boolean retry) {
        logger.info("subAction2");
        canRetry(retry);
    }

    private void canRetry(boolean retry) {
        if (retry) throw new RetryableException("retry service");
    }
}
