package cn.weizhankui;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

/**
 * a pojo,use it to storage email info,such as from,to etc.<br/>
 * you can add File or Inputstream object to send as attachment
 * 
 * @author weizhankui
 * 
 */
public class Email implements Serializable {

	/*
	 * 发件人 mail from
	 */
	private String from;
	/*
	 * 收件人 mail to
	 */
	private String to;
	/*
	 * 主题 mail subject
	 */
	private String subject;
	/*
	 * 内容 mail content
	 */
	private String content;
	/*
	 * 附件map，key是文件名，value是File<br/> attachment map,key is attach name,value is
	 * attachment File
	 */
	private Map<String, File> fileMap;
	/*
	 * 附件map，key是文件名，value是byte[]数组，可以通过调用apache commons
	 * 的IOUtils.toByteArray方法将InputStream转为byte数组 <br/> attachment map,key is
	 * attach name,value is byte[]. you can use IOUtils.toByteArray to switch
	 * InputStream to byte[]
	 */
	private Map<String, byte[]> byteMap;

	public Email(String from, String to, String subject, String content) {
		setFrom(from);
		setTo(to);
		setSubject(subject);
		setContent(content);
	}

	public Email() {

	}

	public Map<String, File> getFileMap() {
		return fileMap;
	}

	public void setFileMap(Map<String, File> fileMap) {
		this.fileMap = fileMap;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, byte[]> getByteMap() {
		return byteMap;
	}

	public void setByteMap(Map<String, byte[]> byteMap) {
		this.byteMap = byteMap;
	}

	private static final long serialVersionUID = 1L;
}
