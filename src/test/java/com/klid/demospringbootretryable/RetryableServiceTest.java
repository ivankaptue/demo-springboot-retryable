package com.klid.demospringbootretryable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

/**
 * @author Ivan Kaptue
 */
@ExtendWith(MockitoExtension.class)
class RetryableServiceTest {

    @Mock
    private NewService newService;

    @InjectMocks
    private RetryableService retryableService;

    @BeforeEach
    void beforeEach() {
        reset(newService);
    }

    @Test
    void testHandleActionNoRetry() {
        doNothing().when(newService).subAction2(anyBoolean());

        retryableService.handleAction(false);

        verify(newService).subAction2(false);
    }
}
