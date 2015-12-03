package com.happytrip.controllers;

import java.io.IOException;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.happytrip.util.StringUtil;

@Controller
public class ContentController {

	@RequestMapping(value = "/aboutus.html", method = RequestMethod.GET)
	public String loadAboutUsPageContent(Locale locale, Model model) {
		
		try {
			StringBuffer aboutUsContent = StringUtil.getAboutUsPageContent("data/aboutus.txt");
			model.addAttribute("aboutuscontent",aboutUsContent);
			StringBuffer advertisementContent = StringUtil.getExternalAdvertisementPageContent("data/advertisement1.txt");
			model.addAttribute("advertisementcontent",advertisementContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "aboutus";
	}
	
	
}
