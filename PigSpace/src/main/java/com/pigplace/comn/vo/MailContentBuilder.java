package com.pigplace.comn.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    @Value("${server.url}")
    private String serverUrl;

    public String signupBuild(String tokenId) {
        Context context = new Context();
        context.setVariable("link", "http://"+ serverUrl +"/member/confirm-email/" + tokenId);
        return templateEngine.process("joinMail", context);
    }
}
