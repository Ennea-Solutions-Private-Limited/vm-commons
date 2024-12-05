package com.ennea.enneaservices.utils;

import com.ennea.enneaservices.constants.Constants;
import com.ennea.enneaservices.model.MailProperties;
import com.ennea.enneaservices.notification.email.BaseEmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.*;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@Component
public class EmailUtils {

    private final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    private final MailProperties mailProperties;

    private final SesV2Client amazonSESClient;

    @Autowired
    public EmailUtils(MailProperties mailProperties, SesV2Client amazonSESClient) {
        this.mailProperties = mailProperties;
        this.amazonSESClient = amazonSESClient;
    }

    public void sendEmail(String subject, String text) {
        send(subject, text, mailProperties.getToAddress());
    }

    public void sendEmail(String subject, String text, String email) {
        send(subject, text, email);
    }

    public void send(String subject, String text, String email) {
        Destination destination = Destination.builder()
            .toAddresses(email)
            .build();

        Content content = Content.builder()
            .data(text).charset("UTF-8")
            .build();

        Content sub = Content.builder()
            .data(subject).charset("UTF-8")
            .build();

        Body body = Body.builder()
            .html(content)
            .build();

        Message msg = Message.builder()
            .subject(sub)
            .body(body)
            .build();

        EmailContent emailContent = EmailContent.builder()
            .simple(msg)
            .build();

        sendEmail(destination, emailContent);
    }

    public String sendTemplatedEmail(BaseEmailTemplate template) {
        return sendTemplatedEmail(template.getTemplate(),
                                  template.generateValueMap(),
                                  template.getTo(),
                                  template.getCcList(),
                                  template.getBccList());
    }

    public String sendTemplatedEmail(String template, String templateData, String email, List<String> ccAddresses,
                                     List<String> bccAddresses) {
        if(ccAddresses != null && !ccAddresses.isEmpty()){
            Destination destination = Destination.builder()
                .toAddresses(email)
                .ccAddresses(ccAddresses)
                .bccAddresses(bccAddresses)
                .build();

            Template emailTemplate = Template.builder()
                .templateName(template)
                .templateData(templateData)
                .build();

            EmailContent emailContent = EmailContent.builder()
                .template(emailTemplate)
                .build();

            return sendEmail(destination, emailContent);
        } else{
            return sendTemplatedEmail(template, templateData, email);
        }
    }

    public String sendTemplatedEmail(String template, String templateData, String email) {
        Destination destination = Destination.builder()
            .toAddresses(email)
            .build();

        Template emailTemplate = Template.builder()
            .templateName(template)
            .templateData(templateData)
            .build();

        EmailContent emailContent = EmailContent.builder()
            .template(emailTemplate)
            .build();

        return sendEmail(destination, emailContent);
    }

    private String sendEmail(Destination destination, EmailContent emailContent) {
        String resp = null;
        SendEmailRequest emailRequest = SendEmailRequest.builder()
            .destination(destination)
            .content(emailContent)
            .fromEmailAddress(mailProperties.getFromAddress())
            .replyToAddresses(Constants.SUPPORT_EMAIL)
            .feedbackForwardingEmailAddress(Constants.SUPPORT_EMAIL)
            .configurationSetName(mailProperties.getConfigSet())
            .build();

        try {
            resp = amazonSESClient.sendEmail(emailRequest).messageId();
        } catch (Exception e) {
            logger.error("Unable to send email {}", e.getMessage());
        }

        return resp;
    }

    public void sendEmailWithAttachment(String recipient, String subject, String body,
                                        MultipartFile attachment) {

        try {

            String attachmentData;
            attachmentData = Base64.getEncoder().encodeToString(attachment.getBytes());


            // Construct the raw email message with attachment
            String emailMessage = "From: "
                                  + mailProperties.getFromAddress()
                                  + "\n"
                                  + "To: "
                                  + recipient
                                  + "\n"
                                  + "Subject: "
                                  + subject
                                  + "\n"
                                  + "MIME-Version: 1.0\n"
                                  + "Content-Type: multipart/mixed; boundary=\"boundary\"\n\n"
                                  + "--boundary\n"
                                  + "Content-Type: text/plain; charset=UTF-8\n\n"
                                  + body
                                  + "\n\n"
                                  + "--boundary\n"
                                  + "Content-Type: application/octet-stream\n"
                                  + "Content-Disposition: attachment; filename=\""
                                  + attachment.getOriginalFilename()
                                  + "\"\n"
                                  + "Content-Transfer-Encoding: base64\n\n"
                                  + attachmentData
                                  + "\n"
                                  + "--boundary--";

            RawMessage rawMessage = RawMessage.builder().data(SdkBytes.fromUtf8String(emailMessage)).build();
            SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .fromEmailAddress(mailProperties.getFromAddress())
                .destination(Destination.builder().toAddresses(recipient).build())
                .content(EmailContent.builder().raw(rawMessage)
                             .build())
                .build();


            amazonSESClient.sendEmail(sendEmailRequest).messageId();

        } catch (Exception e) {
            logger.error("Error while sending email with file to: {} error: {}", recipient, e);
        }

    }

    public void sendEmailWithAttachment(String recipient, String subject, String body, File attachment) {

        try {

            String attachmentData;
            attachmentData = Base64.getEncoder().encodeToString(Files.readAllBytes(attachment.toPath()));


            // Construct the raw email message with attachment
            String emailMessage = "From: " + mailProperties.getFromAddress() + "\n"
                                  + "To: " + recipient + "\n"
                                  + "Subject: " + subject + "\n"
                                  + "MIME-Version: 1.0\n"
                                  + "Content-Type: multipart/mixed; boundary=\"boundary\"\n\n"
                                  + "--boundary\n"
                                  + "Content-Type: text/plain; charset=UTF-8\n\n"
                                  + body + "\n\n"
                                  + "--boundary\n"
                                  + "Content-Type: application/octet-stream\n"
                                  + "Content-Disposition: attachment; filename=\"" + attachment.getName() + "\"\n"
                                  + "Content-Transfer-Encoding: base64\n\n"
                                  + attachmentData + "\n"
                                  + "--boundary--";

            RawMessage rawMessage = RawMessage.builder().data(SdkBytes.fromUtf8String(emailMessage)).build();
            SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .fromEmailAddress(mailProperties.getFromAddress())
                .destination(Destination.builder().toAddresses(recipient).build())
                .content(EmailContent.builder().raw(rawMessage)
                             .build())
                .build();


            amazonSESClient.sendEmail(sendEmailRequest).messageId();

        } catch (Exception e) {
            logger.error("Error while sending email with file to: {} error: {}", recipient, e);

        }

    }
}
