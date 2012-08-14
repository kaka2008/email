package cn.weizhankui.producer;

import cn.weizhankui.Email;

/**
 * message producer
 * 
 * @author weizhankui
 * 
 */
public interface IMessageProducer {
	public void sendUserLoginInformationMail(Email email);
}
