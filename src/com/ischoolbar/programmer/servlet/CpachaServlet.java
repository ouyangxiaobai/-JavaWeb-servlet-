package com.ischoolbar.programmer.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ischoolbar.programmer.util.CpachaUtil;
/**
 * 
 * @author llq
 *ÑéÖ¤Âëservlet
 */
public class CpachaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4919529414762301338L;
	public void doGet(HttpServletRequest request,HttpServletResponse reponse) throws IOException{
		doPost(request, reponse);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse reponse) throws IOException{
		String method = request.getParameter("method");
		if("loginCapcha".equals(method)){
			generateLoginCpacha(request, reponse);
			return;
		}
		reponse.getWriter().write("error method");
	}
	private void generateLoginCpacha(HttpServletRequest request,HttpServletResponse reponse) throws IOException{
		CpachaUtil cpachaUtil = new CpachaUtil();
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute("loginCapcha", generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		ImageIO.write(generatorRotateVCodeImage, "gif", reponse.getOutputStream());
	}
}
