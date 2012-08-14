package cn.weizhankui;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.support.converter.MessageConverter;

/**
 * email converter.convert ActiveMQObjectMessage to Email Object
 * 
 * @author weizhankui
 * 
 */
public class EmailConverter implements MessageConverter {

	@Override
	public Object fromMessage(Message message) throws JMSException {
		if (message instanceof ObjectMessage) {
			ObjectMessage oMsg = (ObjectMessage) message;
			if (oMsg instanceof ActiveMQObjectMessage) {
				ActiveMQObjectMessage aMsg = (ActiveMQObjectMessage) oMsg;
				try {
					Email mime = (Email) aMsg.getObject();
					return mime;
				} catch (Exception e) {
					throw new JMSException("Message:[" + message
							+ "] is not a instance of User.");
				}
			} else {
				throw new JMSException("Message:[" + message + "] is not "
						+ "a instance of ActiveMQObjectMessage[User].");
			}
		} else {
			throw new JMSException("Message:[" + message
					+ "] is not a instance of ObjectMessage.");
		}
	}

	@Override
	public Message toMessage(Object obj, Session session) throws JMSException {
		if (obj instanceof Email) {
			ActiveMQObjectMessage msg = (ActiveMQObjectMessage) session
					.createObjectMessage();
			msg.setObject((Email) obj);
			return msg;
		} else {
			throw new JMSException("Object:[" + obj
					+ "] is not a instance of User.");
		}
	}

}
