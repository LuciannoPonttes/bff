package com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline;

import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model.TimelineIaasResponse;

public interface TimelineIaasService {

    TimelineIaasResponse getTimelineIaasResponse(String tokenCognito,
                                                 String xAccountId,
                                                 String xItauAuth,
                                                 String accountId,
                                                 Integer perPage,
                                                 String afterId,
                                                 String beginDate,
                                                 String endDate);
}
