package com.santi.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * Servlet implementation class CaptchaServlet
 */
@WebServlet("/CaptchaServlet")
public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CaptchaServlet() {
        super();
    }
    
    private DefaultKaptcha defaultKaptcha;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 验证码生成器
		defaultKaptcha = new DefaultKaptcha();
		// 配置
		Properties properties = new Properties();
		// 是否有边框
		properties.setProperty("kaptcha.border", "yes");
		// 设置边框颜色
		properties.setProperty("kaptcha.border.color", "105,179,90");
		// 边框粗细度，默认为1
		// properties.setProperty("kaptcha.border.thickness","1");
		// 验证码
		properties.setProperty("kaptcha.session.key", "code");
		// 验证码文本字符颜色 默认为黑色
		properties.setProperty("kaptcha.textproducer.font.color", "blue");
		// 设置字体样式
		properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		// 字体大小，默认40
		properties.setProperty("kaptcha.textproducer.font.size", "30");
		// 验证码文本字符内容范围 默认为abced2345678gfynmnpwx
		// properties.setProperty("kaptcha.textproducer.char.string", "");
		// 字符长度，默认为5
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		// 字符间距 默认为2
		properties.setProperty("kaptcha.textproducer.char.space", "4");
		// 验证码图片宽度 默认为200
		properties.setProperty("kaptcha.image.width", "100");
		// 验证码图片高度 默认为40
		properties.setProperty("kaptcha.image.height", "40");
		Config c = new Config(properties);
		defaultKaptcha.setConfig(c);
	}




	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 定义response输出类型为image/jpeg类型
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");

        //-------------------生成验证码 begin --------------------------
        //获取验证码文本内容
        String text = defaultKaptcha.createText();
        System.out.println("验证码内容："+text);
        //将验证码文本内容放入session
        request.getSession().setAttribute("captcha",text);
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //输出流输出图片，格式为jpg
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

	}

}
