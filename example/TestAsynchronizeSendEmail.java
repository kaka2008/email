import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.io.IOUtils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import cn.weizhankui.Email;
import cn.weizhankui.producer.IMessageProducer;

/**
 * test send email asynchronous<br/>
 * you must run activemq in your localhost or you have to specify the url in xml
 * 
 * @author weizhankui
 * 
 */
public class TestAsynchronizeSendEmail extends TestCase {
	private static ApplicationContext context = null;
	private static IMessageProducer producer = null;
	private static URL url;
	private static URL xlsUrl;
	// mail from
	private static String MAIL_FROM;
	// mail to
	private static String MAIL_TO;

	protected void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-email.xml");
		producer = (IMessageProducer) context.getBean("topicMessageProducer");
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		url = new URL(path + "/测试.doc");
		xlsUrl = new URL(path + "/测试.xls");
		// FIXME
		// in some mail server ,MAIL_FROM must be same as the username which
		// configured in the xml.
		MAIL_FROM = "weizhankui2008@gmail.com";
		MAIL_TO = "weizhankui2008@gmail.com";
	}

	/**
	 * asynchonous send email
	 */
	public void testSendEmail() {
		producer.sendUserLoginInformationMail(getEmail());
		Assert.assertEquals(true, true);
	}

	public Email getEmail() {
		Email email = new Email(MAIL_FROM, MAIL_TO, "测试", "测试内容");

		Map<String, File> fileMap = new HashMap<String, File>();
		try {
			fileMap.put("测试.doc", new File(url.toURI()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		email.setFileMap(fileMap);

		Map<String, byte[]> byteMap = new HashMap<String, byte[]>();
		try {
			byteMap.put("测试.xls", IOUtils.toByteArray(new FileInputStream(
					new File(xlsUrl.toURI()))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		email.setByteMap(byteMap);
		return email;
	}
}
