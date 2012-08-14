package cn.weizhankui.send;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import cn.weizhankui.Email;
import cn.weizhankui.HtmlMimeMailMessage;

public class EmailSenderImpl implements IEmailSender {

	private static Log logger = LogFactory.getLog(EmailSenderImpl.class);

	public static final String CONTENT_PRIX = "<META http-equiv=Content-Type content='text/html; charset=GBK'>";

	public static final String CONTENT_TYPE = "text/html;charset=GB2312";

	private JavaMailSenderImpl mailSender;

	@Override
	public boolean sendMail(String to, String from, String subject,
			String content) {
		SimpleMailMessage simpleMessage = generateSimpleMessage(to, from,
				subject, content);
		return sendMail(simpleMessage);
	}

	@Override
	public boolean sendMail(String to, String from, String subject,
			Map<String, File> map, String content) {
		SimpleMailMessage simpleMessage = generateSimpleMessage(to, from,
				subject, content);
		return sendMail(map, simpleMessage);
	}

	@Override
	public boolean sendMail(String to, String from, String subject,
			String content, Map<String, InputStream> map) {
		SimpleMailMessage simpleMessage = generateSimpleMessage(to, from,
				subject, content);
		return sendMail(simpleMessage, map);
	}

	@Override
	public boolean sendEmail(Email email) {
		SimpleMailMessage simpleMessage = generateSimpleMessage(email.getTo(),
				email.getFrom(), email.getSubject(), email.getContent());
		return sendMail(getMimeMessage(email.getFileMap(), email.getByteMap(),
				simpleMessage));
	}

	private SimpleMailMessage generateSimpleMessage(String to, String from,
			String subject, String content) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(to.split(","));
		simpleMessage.setFrom(from);
		simpleMessage.setSubject(subject);
		simpleMessage.setText(content);
		return simpleMessage;
	}

	public boolean sendMail(SimpleMailMessage mail) {
		try {
			sendMail(getMimeMessage(mail));
			return true;
		} catch (Exception e) {
			logger.error("sending error!", e);
			return false;
		}
	}

	public boolean sendMail(MimeMessage mail) {
		try {
			mailSender.send(mail);
			return true;
		} catch (Exception e) {
			logger.error("sending error!", e);
			return false;
		}
	}

	private MimeMessage getMimeMessage(SimpleMailMessage mail) {
		try {
			HtmlMimeMailMessage message = null;
			message = new HtmlMimeMailMessage(mailSender.createMimeMessage());
			mail.copyTo(message);
			return message.getMimeMessage();
		} catch (Exception e) {
			logger.error("sending error!", e);
			return null;
		}
	}

	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public boolean sendMail(Map<String, File> map, SimpleMailMessage mail) {
		try {
			sendMail(getMimeMessage(mail, map));
			return true;
		} catch (Exception e) {
			logger.error("sending error!", e);
			return false;
		}
	}

	private MimeMessage getMimeMessage(SimpleMailMessage mail,
			Map<String, File> map) {
		try {
			HtmlMimeMailMessage message = null;
			message = new HtmlMimeMailMessage(mailSender.createMimeMessage());
			if (map != null) {
				for (Iterator<Map.Entry<String, File>> ite = map.entrySet()
						.iterator(); ite.hasNext();) {
					Map.Entry<String, File> entry = ite.next();
					message.addAttachment(
							MimeUtility.encodeWord(entry.getKey()),
							entry.getValue());
				}
			}
			mail.copyTo(message);
			return message.getMimeMessage();
		} catch (Exception e) {
			logger.error("sending error!", e);
			return null;
		}
	}

	private MimeMessage getMimeMessage(Map<String, InputStream> map,
			SimpleMailMessage mail) {
		try {
			HtmlMimeMailMessage message = null;
			message = new HtmlMimeMailMessage(mailSender.createMimeMessage());
			if (map != null) {
				for (Iterator<Map.Entry<String, InputStream>> ite = map
						.entrySet().iterator(); ite.hasNext();) {
					Map.Entry<String, InputStream> entry = ite.next();
					message.addAttachment(
							MimeUtility.encodeWord(entry.getKey()),
							entry.getValue());
				}
			}
			mail.copyTo(message);
			return message.getMimeMessage();
		} catch (Exception e) {
			logger.error("sending error!", e);
			return null;
		}
	}

	public boolean sendMail(SimpleMailMessage mail, Map<String, InputStream> map) {
		try {
			sendMail(getMimeMessage(map, mail));
			return true;
		} catch (Exception e) {
			logger.error("sending error!", e);
			return false;
		}
	}

	private MimeMessage getMimeMessage(Map<String, File> fileMap,
			Map<String, byte[]> isMap, SimpleMailMessage mail) {
		try {
			HtmlMimeMailMessage message = null;
			message = new HtmlMimeMailMessage(mailSender.createMimeMessage());
			if (fileMap != null && fileMap.size() > 0) {
				for (Iterator<Map.Entry<String, File>> ite = fileMap.entrySet()
						.iterator(); ite.hasNext();) {
					Map.Entry<String, File> entry = ite.next();
					message.addAttachment(
							MimeUtility.encodeWord(entry.getKey()),
							entry.getValue());
				}
			}
			if (isMap != null && isMap.size() > 0) {
				for (Iterator<Map.Entry<String, byte[]>> ite = isMap.entrySet()
						.iterator(); ite.hasNext();) {
					Map.Entry<String, byte[]> entry = ite.next();
					message.addAttachment(
							MimeUtility.encodeWord(entry.getKey()),
							entry.getValue());
				}
			}
			mail.copyTo(message);
			return message.getMimeMessage();
		} catch (Exception e) {
			logger.error("sending error!", e);
			return null;
		}
	}
}
