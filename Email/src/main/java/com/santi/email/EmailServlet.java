package com.santi.email;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.util.MimeMessageParser;
import com.alibaba.fastjson2.JSON;
import com.santi.constants.ApplicationConstants;
import com.santi.enums.UserEnum;
import com.santi.pojo.ReceiveUser;
import com.santi.pojo.SendUser;
import com.santi.pojo.User;
import com.santi.service.ReceiveService;
import com.santi.service.SendUserSerivce;
import com.santi.service.UserSerivce;
import com.santi.service.imp.ReceiveServiceImp;
import com.santi.service.imp.SendUserSerivceImp;
import com.santi.service.imp.UserServiceImp;
import com.santi.util.BaseServlet;

@WebServlet("/emailServlet")
public class EmailServlet extends BaseServlet {

	private UserSerivce userService = new UserServiceImp();
	private SendUserSerivce sendUserSerivce = new SendUserSerivceImp();
	private ReceiveService receiveService = new ReceiveServiceImp();
	private ReceiveUser receiveUser = new ReceiveUser();
//定义一些全局变量来接收数据

	List<SendUser> listUsers = new ArrayList<>();

	/**
	 * 发送
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ParseException
	 */
	public void sendEamil(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
		String sendUser = req.getParameter("sendUser");//接收人
		String title = req.getParameter("title");
		String sendContent = req.getParameter("sendContent");

		// 第三方密码1365137894@qq.com
		String treePwd = "lzpzudhdlwjddhei";
		Integer key = (Integer) req.getSession().getAttribute(ApplicationConstants.USER_KEY);
		if (key != null) {
			int j = userService.inTreePwd(treePwd, key);
			if (j > 0) {
				SendUser user = new SendUser();
				int i = sendUserSerivce
						.receiveSendUserMsg(new SendUser(sendUser, title, user.getSendDate(), sendContent, key));
				//调用发送邮箱方法
				User byKeyGetEmail = userService.byKeyGetEmail(key);
				
				if(byKeyGetEmail!=null) {
				send(byKeyGetEmail.getUserEmail(),sendUser, treePwd, title, sendContent);
				System.out.println("asdsassssssssssss");
				}
//				
				if (i > 0)
					resp.getWriter().write(JSON.toJSONString(UserEnum.SUCCEE.getNumber()));
				else
					resp.getWriter().write(JSON.toJSONString(UserEnum.FAILURE.getNumber()));
			}
		}
	}

	/**
	 * 接收方法
	 * 
	 * @param res
	 * @param resp
	 */
	public void receiveEmail(HttpServletRequest req, HttpServletResponse resp) {
		receive();
		try {
			Object key = req.getSession().getAttribute(ApplicationConstants.USER_KEY);
			System.out.println(key);
			System.out.println(receiveUser.getReceiveEmail());
			System.out.println(receiveUser.getReceiveTitle());
			System.out.println(receiveUser.getReceiveContent());
			boolean b = receiveService
					.addReceiveUser(new ReceiveUser(receiveUser.getReceiveEmail(), receiveUser.getReceiveTitle(),
							receiveUser.getReceiveContent(), receiveUser.getReceiveDate(), (Integer) key));

			if (b) {
				System.out.println("添加成功");
				List<ReceiveUser> list = receiveService.showReceiveUser();
				resp.getWriter().write(JSON.toJSONString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除方法
	 * 
	 * @throws IOException
	 */
	public void removeEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 接收前台传来id字符串并进行分割
		String str = req.getParameter("id");
		str = str.trim();
		String[] split = str.split(",");
//		传到ids数组中
		int ids[] = new int[split.length - 1];

		for (int i = 0; i < ids.length; i++) {
			String string = split[i + 1];
			ids[i] = Integer.parseInt(string);
		}
		int i = receiveService.removeEmail(ids);
		if (i > 0) {
			resp.getWriter().write(JSON.toJSONString(UserEnum.SUCCEE.getNumber()));
		} else {
			resp.getWriter().write(JSON.toJSONString(UserEnum.FAILURE.getNumber()));
		}
	}

	/**
	 * removeEmailList 删除指定id的list集合
	 * 
	 * @throws IOException
	 */
	public void removeEmailList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String ids = req.getParameter("ids");
		ids = ids.trim();
		String[] split = ids.split(",");
		String id[] = new String[split.length - 1];

		for (int i = 0; i < id.length; i++) {
			id[i] = split[i + 1];
		}

		// 循环删除
		for (int i = 0; i < listUsers.size(); i++) {
			for (int j = 0; j < id.length; j++) {
				if (id[j].equals(listUsers.get(i).getSendEmail())) {
					listUsers.remove(i);
				}
			}
		}

		resp.getWriter().write(JSON.toJSONString(UserEnum.SUCCEE.getNumber()));

	}

	/**
	 * 存草稿方法
	 * 
	 * @throws IOException
	 */
	public void saveDraft(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String sendUser = req.getParameter("sendUser");
		String title = req.getParameter("title");
		String sendContent = req.getParameter("sendContent");
		
		SendUser user = new SendUser(sendUser, title, sendContent);

		if (!"".equals(sendUser) && !"".equals(title) && !"".equals(sendContent)) {
			//草稿邮箱
			req.getSession().setAttribute("user", user);
			// 件对象存入list集合中用来处理垃圾邮箱
			listUsers.add(user);
			resp.getWriter().write(JSON.toJSONString(UserEnum.SUCCEE.getNumber()));
			return;
		}
	}

	/**
	 * 获取user信息
	 * 
	 * @throws IOException
	 */
	public void getUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SendUser user = (SendUser) req.getSession().getAttribute("user");
		if (user != null)
			resp.getWriter().write(JSON.toJSONString(user));
		else
			resp.getWriter().write(JSON.toJSONString(UserEnum.FAILURE.getNumber()));

	}

	/**
	 * 获取getListUser信息
	 * 
	 * @throws IOException
	 */
	public void getListUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		for (SendUser sendUser : listUsers) {
			System.out.println(sendUser);
		}
		if (this.listUsers != null) {
			resp.getWriter().write(JSON.toJSONString(this.listUsers));
		} else {
			resp.getWriter().write(JSON.toJSONString(UserEnum.FAILURE.getNumber()));
		}
	}

	/**
	 * 摧毁对象user
	 */
	public void destroyUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SendUser user = (SendUser) req.getSession().getAttribute("user");
		if (user != null) {
			HttpSession session = req.getSession();
			session.removeAttribute("user");
			resp.getWriter().write(JSON.toJSONString(UserEnum.SUCCEE.getNumber()));
		} else {
			resp.getWriter().write(JSON.toJSONString(UserEnum.FAILURE.getNumber()));
		}
	}
	/**
	 * 注销（退出）
	 */
	public void cancellation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		session.removeAttribute(ApplicationConstants.USER_KEY);
		resp.getWriter().write(JSON.toJSONString(UserEnum.SUCCEE.getNumber()));
	}
	
	/**
	 * 总邮件数
	 */
	public void TotalEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int i = receiveService.totalEmail();
		if(i>0) 
			resp.getWriter().write(JSON.toJSONString(i));
		else
			resp.getWriter().write(JSON.toJSONString(i));
	}
	
	
	
	/***
	 * 发送邮件
	 */
	public static void send(String sendUser,String reivceUser, String treePwd, String title, String content) {
		try {
			String host = "smtp.qq.com";// 这是QQ邮箱的smtp服务器地址
			String port = "25"; // 端口号

			/* *Properties是一个属性对象，用来创建Session对象 */
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", host);
			props.setProperty("mail.smtp.port", port);
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.ssl.enable", "false");// "true"
			props.setProperty("mail.smtp.connectiontimeout", "5000");

			final String user = sendUser;// 用户名
			final String pwd = treePwd;// 密码

			/* *Session类定义了一个基本的邮件对话。 */
			Session session = Session.getInstance(props, new Authenticator() {
				@Override

				protected PasswordAuthentication getPasswordAuthentication() {
					// 登录用户名密码
					return new PasswordAuthentication(user, pwd);
				}

			});

			session.setDebug(true);

			/* *Transport类用来发送邮件。 *传入参数smtp，transport将自动按照smtp协议发送邮件。 */

			Transport transport = session.getTransport("smtp");// "smtps"
			transport.connect(host, user, pwd);

			/* *Message对象用来储存实际发送的电子邮件信息 */
			MimeMessage message = new MimeMessage(session);
			message.setSubject(title);// 标题

			// 消息发送者接收者设置(发件地址，昵称)，收件人看到的昵称是这里设定的
			message.setFrom(new InternetAddress(user, "俊成"));
			message.addRecipients(Message.RecipientType.TO, new InternetAddress[] {

					// 消息接收者(收件地址，昵称)
					// 不过这个昵称貌似没有看到效果
//					new InternetAddress("3386683736@qq.com","石航名"),
					new InternetAddress(reivceUser, "小朱"), });

			message.saveChanges();
			// 设置邮件内容及编码格式
			// 后一个参数可以不指定编码，如"text/plain"，但是将不能显示中文字符
			message.setContent(content, "text/html;charset=UTF-8");

			// 发送
			// transport.send(message);
			Transport.send(message);
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Properties getIMAP() {
		String host = "imap.qq.com";
		Properties p = new Properties();
		p.setProperty("mail.transport.protocol", "imap");
		p.setProperty("mail.imap.host", host); // 按需要更改
		p.setProperty("mail.imap.port", "993");

		// SSL安全连接参数
		p.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.setProperty("mail.imap.socketFactory.fallback", "false");
		p.setProperty("mail.imap.socketFactory.port", "993");

		return p;
	}

	/***
	 * 接收邮件
	 */
	public void receive() {

//		String protocol = "pop3";//使用pop3协议
//		boolean isSSL = true;//使用SSL加密
//		String host = "pop.qq.com";//QQ邮箱的pop3服务器
//		int port = 995;//端口

		String username = "2770085055@qq.com";// 用户名
		String password = "oqzndbfwchbldejg";// 密码

		/* *Properties是一个属性对象，用来创建Session对象 */

//		Properties props = new Properties();
//		props.put("mail.pop3.ssl.enable", isSSL);
//		props.put("mail.pop3.host", host);
//		props.put("mail.pop3.port", port);

		Properties props = getIMAP();

		/* *Session类定义了一个基本的邮件对话。 */

		Session session = Session.getDefaultInstance(props);

		/*
		 * * Store类实现特定邮件协议上的读、写、监视、查找等操作。 * 通过Store类可以访问Folder类。 *
		 * Folder类用于分级组织邮件，并提供照Message格式访问email的能力。
		 */

		Store store = null;
		Folder folder = null;

		try {
			store = session.getStore("imap");
			store.connect(username, password);
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);// 在这一步，收件箱所有邮件将被下载到本地

			int size = folder.getMessageCount();// 获取邮件数目
			System.out.println("邮件大小：" + size);
			Message message = folder.getMessage(size);// 取得最新的那个邮件

			MimeMessageParser parser = new MimeMessageParser((MimeMessage) message).parse();
//			MimeMultipart mmp = (MimeMultipart) message.getContent();
			// 解析邮件内容
			System.out.println("内容");
			receiveUser.setReceiveEmail(parser.getFrom());
			receiveUser.setReceiveTitle(parser.getSubject());
			receiveUser.setReceiveContent(parser.getPlainContent());
			System.out.println(receiveUser);
			System.out.println(parser.getFrom());
			System.out.println(parser.getPlainContent());
			System.out.println(parser.getSubject());

//			String from = message.getFrom()[0].toString();
//
//			String subject = message.getSubject();

//			Date date = message.getSentDate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (folder != null) {
					folder.close(false);
				}

				if (store != null) {
					store.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("接收完毕！");
	}

//	public static void main(String[] args) {
//		receive();
//		send();
//	}

}
