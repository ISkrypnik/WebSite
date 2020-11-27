package com.ilyaskrypnik.website.service;

import com.ilyaskrypnik.website.feign.MailServiceFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmailService {

    private final MailServiceFeignClient mailServiceFeignClient;

    public void sendSuccessRegistrationEmail(Long userId) {
        try {
            log.info("Попытка отправки почтового уведомления об успешной регистрации для пользователя с id = {}.", userId);
            mailServiceFeignClient.sendRegistrationSuccessEmail(userId);
        } catch (Exception e) {
            log.error("Ошибка отправки почтового уведомления через сервис отправки писем.", e);
        }
    }
}
