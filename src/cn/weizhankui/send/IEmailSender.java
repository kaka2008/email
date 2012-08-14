package cn.weizhankui.send;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import cn.weizhankui.Email;

/**
 * email sender
 * 
 * @author weizhankui
 * @since 2012-8-12
 */
public interface IEmailSender {
	/**
	 * synchronize send email without attachment
	 * 
	 * @param to
	 * @param from
	 * @param subject
	 * @param content
	 * @return
	 */
	public boolean sendMail(String to, String from, String subject,
			String content);

	/**
	 * synchronize send email with attachment map.<br>
	 * in the map,key is attach name,value is attach file
	 * 
	 * @param to
	 * @param from
	 * @param subject
	 * @param map
	 * @param content
	 * @return
	 */
	public boolean sendMail(String to, String from, String subject,
			Map<String, File> map, String content);

	/**
	 * synchronize send email with attachment map.<br>
	 * in the map,key is attach name,value is attach inputstream
	 * 
	 * @param to
	 * @param from
	 * @param subject
	 * @param content
	 * @param map
	 * @return
	 */
	public boolean sendMail(String to, String from, String subject,
			String content, Map<String, InputStream> map);

	/**
	 * synchronize send email use Email object.. use it when asynchronize send
	 * email
	 * 
	 * @param email
	 * @return
	 */
	public boolean sendEmail(Email email);

}
