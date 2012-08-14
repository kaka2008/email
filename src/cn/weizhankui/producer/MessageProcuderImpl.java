package cn.weizhankui.producer;

import javax.jms.Destination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;

import cn.weizhankui.Email;

public class MessageProcuderImpl implements IMessageProducer {

	private static Log logger = LogFactory.getLog(MessageProcuderImpl.class);

	private JmsTemplate jmsTemplate;
	private Destination destination;

	@Override
	public void sendUserLoginInformationMail(Email email) {
		logger.info("sending message...");
		jmsTemplate.convertAndSend(this.destination, email);
	}

	public final JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public final void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

}
