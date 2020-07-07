/*
 * Listener to handle Exception occurs during Step Execution
 * @Auther : Prabhu Sahu
 * */
package com.nordea.assessment.config;

import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StepErrorLoggingListener implements StepExecutionListener {

   

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // do nothing.
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        List<Throwable> exceptions = stepExecution.getFailureExceptions();
        if (exceptions.isEmpty()) {
            return ExitStatus.COMPLETED;
        }
        log.info("This step has occurred some exceptions as follow. " +
                "[step-name:{}] [size:{}]",
                stepExecution.getStepName(), exceptions.size());
        exceptions.forEach(th -> log.error(
                "exception has occurred in job.", th));
        return ExitStatus.FAILED;
    }
}