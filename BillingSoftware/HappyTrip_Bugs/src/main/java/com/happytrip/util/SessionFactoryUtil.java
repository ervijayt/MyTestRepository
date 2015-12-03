package com.happytrip.util;

import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
public class SessionFactoryUtil extends HttpServlet{

	private static WebApplicationContext wac;
	private static SessionFactory sFactory = null;
	
	public static void setSessionFactor(SessionFactory sessionFactory){
		sFactory = sessionFactory;
	}
	
	private static void initContext(ServletContext servletContext){
		wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ServletContext servletContext = this.getServletContext();
		initContext(servletContext);
	}

	public static HibernateTemplate getHibernateTemplate(){
		if(sFactory == null){
			sFactory = (SessionFactory) wac.getBean("sessionFactory");
		}
		return new HibernateTemplate(sFactory);
	}

}
