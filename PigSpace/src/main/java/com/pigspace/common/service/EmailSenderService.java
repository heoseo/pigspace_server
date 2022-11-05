package com.pigspace.common.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pigspace.common.support.PigException;
import com.pigspace.common.support.ServiceSupport;
import com.pigspace.member.vo.EmailVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSenderService extends ServiceSupport{

	private static final long serialVersionUID = 8750303270298680716L;

	private final JavaMailSender javaMailSender;

	@Async
	public void sendEmail(EmailVO emailVO) throws Exception {


		MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//            messageHelper.setFrom("보내는 사람 이메일 ");
            messageHelper.setTo(emailVO.getReceiverEmail());
            messageHelper.setSubject(emailVO.getSubject());
            messageHelper.setText(emailVO.getText(), true);

            debug("##############"+emailVO.getText());
        };

        try {
        	javaMailSender.send(messagePreparator);
//            log.info("활성화 메일이 보내졌다");
        } catch (MailException e) {
//            log.error(String.valueOf(e));
        	e.printStackTrace();
            throw new PigException("메일을 여기로 보내는 중 에러 발생 :  " + emailVO.getReceiverEmail());
        }
	}
}