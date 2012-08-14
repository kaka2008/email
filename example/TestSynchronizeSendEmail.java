import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import cn.weizhankui.send.IEmailSender;

/**
 * Test send email synchronizely.
 * 
 * @author weizhankui
 * 
 */
public class TestSynchronizeSendEmail extends TestCase {

	private static ApplicationContext context = null;
	private static IEmailSender emailSender = null;
	private static URL url;
	// mail from
	private static String MAIL_FROM;
	// mail to
	private static String MAIL_TO;

	protected void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-email.xml");
		emailSender = (IEmailSender) context.getBean("emailSender");
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		url = new URL(path + "/测试.doc");
		// FIXME
		// in some mail server ,MAIL_FROM must be same as the username which
		// configured in the xml.
		MAIL_FROM = "weizhankui2008@gmail.com";
		MAIL_TO = "weizhankui2008@gmail.com";
	}

	/**
	 * send common email with out attach
	 */
	public void testSendCommonEmailWithoutAttach() {
		Assert.assertEquals(true,
				emailSender.sendMail(MAIL_TO, MAIL_FROM, "测试", "测试内容"));
	}

	/**
	 * send email with File attach
	 */
	public void testSendEmailWithFileAttach() {
		Map<String, File> attMap = new HashMap<String, File>();
		try {
			attMap.put("测试.doc", new File(url.toURI()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true,
				emailSender.sendMail(MAIL_TO, MAIL_FROM, "测试", attMap, "测试内容"));
	}

	/**
	 * send email with InputStream attach
	 */
	public void testSendEmailWithInputStreamAttach() {
		Map<String, InputStream> attMap = new HashMap<String, InputStream>();
		try {

			attMap.put("测试.doc", new FileInputStream(new File(url.toURI())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true,
				emailSender.sendMail(MAIL_TO, MAIL_FROM, "测试", "测试内容", attMap));
	}

}
