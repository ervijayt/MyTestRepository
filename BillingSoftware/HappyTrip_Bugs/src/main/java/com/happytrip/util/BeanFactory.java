package com.happytrip.util;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.happytrip.services.AdminService;

public final class BeanFactory implements ServletContextListener{
	private static final long serialVersionUID = 6426614044035008517L;
	private  Document doc;
	private  static Map<String, Object> pool = new HashMap<String, Object>();

	
	public void init() {
		System.out.println("Int....");
		SAXReader reader = new SAXReader();
		try {
			InputStream is = BeanFactory.class.getClassLoader()
					.getResourceAsStream("spring/bean-mapping.xml");
			doc = reader.read(is);
			String xpathExpression = "//beansmapping/bean";
			List<Node> nodes = doc.selectNodes(xpathExpression);
			for (Node node : nodes) {
				String className = node.getText();
				Element elem = (Element)node;
				String value = (String)elem.attributes().get(0).toString();
					pool.put(value.split("\"")[1],Class.forName(className).newInstance());	
			}
			
			System.out.println("Init com..");
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BeanFactory beanFactory;
	
	public static Object getBean(String name) {
	
		System.out.println("Name"+name);
		System.out.println("Pool"+pool);
		return pool.get(name);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		init();
		
	}


}

