package kr.ac.ajou.mydictionary.dictionarymanager;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.ajou.mydictionary.user.UserModel;
import kr.ac.ajou.mydictionary.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public UserModel login(HttpServletRequest request, HttpServletResponse response) {
		logger.info("[/login]" + " - " + "Get in login method");
		try {
			UserModel userModel = userService.login(request.getParameter("idtoken"));
			if (userModel != null) { // idTokenString
				logger.info("[/login]" + " - " + "success");

				return userModel;
			}
		} catch (Exception e) {
			logger.info("[/login]" + " - " + "Catch Exception:", e);
		}

		logger.info("[/login]" + " - " + "fail");
		return null;
	}

	@RequestMapping(value = "/getUserByEmail", method = RequestMethod.POST) //{emailID}
	public @ResponseBody UserModel findUserByEmail(@RequestBody String email) {
		logger.info("[/getUserByEmail]" + " - " + "Get in findUserByEmail method");
		logger.info("[/getUserByEmail]" + " - " + email);
		
		// String emailStr = email + "@" + domain.replace("=", "");
		// System.err.println(emailStr);
		// logger.info("emailStr=" + emailStr);
		// email = URLDecoder.decode(email,"UTF-8");

		UserModel user = userService.getUserByEmail(email);
		logger.info("[/getUserByEmail]" + " - " + user.toString());

		return user;
	}

	@RequestMapping(value = "/loginTestFlow", method = RequestMethod.GET)
	public ModelAndView loginTestFlow(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("testFlow/userTest");
		// mv.addObject("loginBean", userService.getUserByEmail());
		
		return mv;
	}
}
