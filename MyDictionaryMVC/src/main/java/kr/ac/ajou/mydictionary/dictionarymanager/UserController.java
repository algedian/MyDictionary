package kr.ac.ajou.mydictionary.dictionarymanager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.ajou.mydictionary.user.UserModel;
import kr.ac.ajou.mydictionary.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("Get in login method");
		logger.info("Get in login method");
		try {
			if (userService.login(request.getParameter("idtoken"))) { // idTokenString
				logger.info("success");
				logger.info(userService.getCurrentUser().toString());
				
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("fail");
		return "fail";
	}

	@RequestMapping(value = "/{emailID}", method = RequestMethod.POST)
	// , produces="application/json" , consumes="text/plain")
	public @ResponseBody UserModel findUserByEmail(@PathVariable String emailID, @RequestBody String domain) {

		// {}
		System.err.println("Get in findUserByEmail method");
		logger.info("Get in findUserByEmail method");

		UserModel user = null;

		try {
			String emailStr = emailID + "@" + domain.replace("=", "");
			System.err.println(emailStr);
			logger.info("emailStr=" + emailStr);
			// email = URLDecoder.decode(email,"UTF-8");

			user = userService.getUserByEmail(emailStr);
			System.err.println(user.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(user.toString());
		return user;
	}
	
	@RequestMapping(value = "loginTestFlow", method = RequestMethod.GET)
	public ModelAndView loginTestFlow(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("testFlow/userTest");
		mv.addObject("user", userService.getCurrentUser());
		
		return mv;
	}
}
