package cn.weizhankui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

public class HtmlMimeMailMessage extends MimeMailMessage {

	private final MimeMessageHelper helper;
	private static Log logger = LogFactory.getLog(HtmlMimeMailMessage.class);

	public HtmlMimeMailMessage(MimeMessageHelper arg0) {
		super(arg0);
		this.helper = arg0;
	}

	public HtmlMimeMailMessage(MimeMessage mimeMessage) {
		super(mimeMessage);
		MimeMessageHelper subHelper = null;
		try {
			subHelper = new MimeMessageHelper(mimeMessage, true, "GBK");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		this.helper = subHelper;
	}

	public void setText(String text) throws MailParseException {
		try {
			helper.setText(text, true);
		} catch (MessagingException ex) {
			throw new MailParseException(ex);
		}
	}

	public void addAttachment(String name, final File file) {
		try {
			this.helper.addAttachment(name, file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void addAttachment(String name, byte[] b) {
		try {
			this.helper.addAttachment(name, new ByteArrayResource(b));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void addAttachment(String name, final InputStream is) {
		try {
			this.helper.addAttachment(name,
					new ByteArrayResource(IOUtils.toByteArray(is)));
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}