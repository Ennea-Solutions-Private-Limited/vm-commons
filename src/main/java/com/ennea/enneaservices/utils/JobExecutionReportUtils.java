package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.model.JobExecutionReport;
import com.ennea.enneaservices.model.User;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.StringJoiner;

@Component
public class JobExecutionReportUtils {

    public static JobExecutionReport prepareJobExecutionReport(@NonNull final User supplier, @NonNull final String type,
                                                               @NonNull final String status,
                                                               @NonNull final LocalDateTime executionTime,
                                                               final int recordsModified, final String message) {
        final JobExecutionReport jobExecutionReport = new JobExecutionReport();
        jobExecutionReport.setSupplier(supplier);
        jobExecutionReport.setType(type);
        jobExecutionReport.setStatus(status);
        jobExecutionReport.setExecutionDateTime(executionTime);
        jobExecutionReport.setRecordsModified(recordsModified);
        jobExecutionReport.setMessage(message);
        return jobExecutionReport;
    }

    public static String prepareFailureStackTrace(@NonNull final Exception exception) {
        final String[] traceElements = ExceptionUtils.getRootCauseStackTrace(exception);
        final StringJoiner traceJoiner = new StringJoiner("\n");
        for(String traceElement : traceElements){
            traceJoiner.add(traceElement);
        }
        return StringUtils.abbreviate(traceJoiner.toString(), 1024);
    }
}
