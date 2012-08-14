package cn.weizhankui.consumer;

import javax.jms.JMSException;

import cn.weizhankui.Email;

/**
 * message consumer
 * 
 * @author weizhankui
 * 
 */
public interface IMessageConsumer {
	public void handleMessage(Email email) throws JMSException;
}
