package kr.ac.ajou.mydictionary.dictionarymanager;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.ajou.mydictionary.dictionarydata.DictionaryDataFacade;
import kr.ac.ajou.mydictionary.document.DocumentService;
import kr.ac.ajou.mydictionary.searchengine.SearchEngine;
import kr.ac.ajou.mydictionary.user.UserModel;
import kr.ac.ajou.mydictionary.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userService")
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("Get in login method");
		try {
			if(userService.login(request.getParameter("idtoken"))) //idTokenString
				return "success";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "fail";
	}
	
	@RequestMapping(value = "/{emailID}", method = RequestMethod.POST, produces="application/json" , consumes="text/plain")
	public @ResponseBody UserModel findUserByEmail(@PathVariable String emailID, @RequestBody String domain ) {
		System.err.println("Get in findUserByEmail method");
		UserModel user = null;
		
		try {
			String emailStr = emailID +"@"+ domain.replace("=","");
			System.err.println(emailStr);
			//email = URLDecoder.decode(email,"UTF-8");

			user = userService.getUserByEmail(emailStr);
			System.err.println(user.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	@RequestMapping(value = "/loginTest", method = RequestMethod.GET)
	public ModelAndView loginTest(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("Get in loginTest method");
		ModelAndView mv = new ModelAndView("loginTest");

		return mv;
	}
}
