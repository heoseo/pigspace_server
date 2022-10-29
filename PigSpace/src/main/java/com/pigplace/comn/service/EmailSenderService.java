package com.pigplace.comn.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pigplace.common.support.PigException;
import com.pigplace.member.vo.EmailVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

	private final JavaMailSender javaMailSender;

	@Async
	public void sendEmail(EmailVO emailVO) throws Exception {


		MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//            messageHelper.setFrom("보내는 사람 이메일 ");
            messageHelper.setTo(emailVO.getReceiverEmail());
            messageHelper.setSubject(emailVO.getSubject());
            messageHelper.setText(emailVO.getText(), true);

            System.out.println("##############"+emailVO.getText());
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