package com.ilyaskrypnik.website.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "${application.external-service.email-service.name}", url = "${application.external-service.email-service.main-uri}")
public interface MailServiceFeignClient {

    @PostMapping(value = "${application.external-service.email-service.send-registration-success-email")
    ResponseEntity<?> sendRegistrationSuccessEmail(@PathVariable(value = "userId") Long userId);
}
