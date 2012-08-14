package cn.weizhankui.consumer;

import javax.jms.JMSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.weizhankui.Email;
import cn.weizhankui.send.IEmailSender;

public class MessageConsumerImpl implements IMessageConsumer {

	private static Log logger = LogFactory.getLog(MessageConsumerImpl.class);

	private IEmailSender emailSender;

	@Override
	public void handleMessage(Email email) throws JMSException {
		logger.info("receive message...");
		boolean flag = emailSender.sendEmail(email);
		if (flag) {
			logger.info("email send success!");
		} else {
			logger.error("email send error!");
		}

	}

	public IEmailSender getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(IEmailSender emailSender) {
		this.emailSender = emailSender;
	}

}
