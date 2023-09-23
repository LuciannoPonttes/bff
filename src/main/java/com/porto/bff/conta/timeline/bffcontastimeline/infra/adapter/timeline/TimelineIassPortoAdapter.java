package com.porto.bff.conta.timeline.bffcontastimeline.infra.adapter.timeline;

import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model.TimelineIaasResponse;

public interface TimelineIassPortoAdapter {

    TimelineIaasResponse getTimelineIaasResponse(String tokenCognito,
                                                 String xAccountId,
                                                 String xItauAuth,
                                                 String accountId,
                                                 Integer perPage,
                                                 String afterId,
                                                 String beginDate,
                                                 String endDate);
}
