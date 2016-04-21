package kr.ac.ajou.mydictionary.dictionarymanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@RequestMapping(value = "/")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView mv = new ModelAndView("demo/login");	
		return mv;
	}
	
	@RequestMapping(value = "/home")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView mv = new ModelAndView("demo/home");	
		return mv;
	}
	
	@RequestMapping(value = "/friend")
	public ModelAndView friend(HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView mv = new ModelAndView("demo/friend");	
		return mv;
	}
	
	@RequestMapping(value = "/document")
	public ModelAndView document(HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView mv = new ModelAndView("demo/document");	
		return mv;
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView mv = new ModelAndView("demo/search");	
		return mv;
	}
}
