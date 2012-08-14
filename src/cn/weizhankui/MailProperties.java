package cn.weizhankui;

import java.util.Properties;

public class MailProperties extends Properties {

	private static final long serialVersionUID = 1L;

	public MailProperties(String auth) {
		super.setProperty("mail.smtp.auth", auth);
	}

}
