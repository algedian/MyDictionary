package kr.ac.ajou.mydictionary.dictionarymanager;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.ajou.mydictionary.dictionarydata.DictionaryDataFacade;
import kr.ac.ajou.mydictionary.document.DocumentService;
import kr.ac.ajou.mydictionary.searchengine.SearchEngine;
import kr.ac.ajou.mydictionary.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource(name = "userService")
	private UserService userService;
	
/*	@Resource(name = "dictionaryDataBaseFacade")
	DictionaryDataFacade dictionaryDataFacade;

	public void documentSetTest() {
		dictionaryDataFacade.setDictionary("dcoun", "dictionary", "sdaflkajsdglkhdfglkjsdfhglkjsdfghlkdfsjh");
	}*/
	
//	@Resource(name = "documentService")
//	private DocumentService documentService;
	
	//@Resource(name = "searchEngine")
	//private SearchEngine searchEngine;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		ModelAndView mv = new ModelAndView("home");
		LoginBean loginBean = new LoginBean();
		
		mv.addObject("loginBean", loginBean);
		
		return mv;
	}

	/*
	@RequestMapping(value = "/loginTest", method = RequestMethod.POST)
	public ModelAndView loginTest(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean")LoginBean loginBean) {
		try {
			logger.info(loginBean.getUserId());
			userService.loginTest(loginBean.getUserId());
			ModelAndView mv = new ModelAndView();
			
			String result = "";
			mv.addObject(result);
			
			return mv;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// exception 발생시 실패 페이지로 이동 하면 될듯
		}
		
		return null;
	}*/
	
	@RequestMapping(value = "/dictionaryTest", method = RequestMethod.GET)
	public ModelAndView dictionaryTest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
//		documentService.documentSetTest();
		
		return mv;
	}
	
//	@RequestMapping(value = "/dictionarySearchTest", method = RequestMethod.GET)
//	public ModelAndView dictionarySearchTest(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mv = new ModelAndView();
		
		// searchEngine
		
//		return mv;
//	}
}
