package kr.ac.ajou.mydictionary.user;

import java.util.Arrays;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import kr.ac.ajou.mydictionary.userdata.UserDataFacade;

//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service("userService")
public class UserServiceImpl implements UserService {
	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource(name = "userDataBaseFacade")
	private UserDataFacade userDataFacade;

	private static final String CLIENT_ID = "768889397569-ifca7s46aplo8i4ikt95ba6ihjmmfdlf.apps.googleusercontent.com";

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport httpTransport;

	public UserServiceImpl() {
		super();
	}

	@Override
	public UserModel login(String idTokenString) throws Exception {
		httpTransport = GoogleNetHttpTransport.newTrustedTransport();

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JSON_FACTORY)
				.setAudience(Arrays.asList(CLIENT_ID)).setIssuer("accounts.google.com").build();

		GoogleIdToken idToken = verifier.verify(idTokenString);

		if (idToken != null) {
			Payload payload = idToken.getPayload();

			// Get user identifier
			String userId = payload.getSubject();

			// Get profile information from payload
			String email = payload.getEmail();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String name = (String) payload.get("name");
			String pictureURL = (String) payload.get("picture");

			UserModel user;
			if (emailVerified) {
				if ((user = userDataFacade.selectUserById(userId)) != null) {
					return user;// login success
				} else {
					// -1 is just init value.
					// When insert process, facade does not read id so perhaps it is ok.
					user = new UserModel(-1, userId, name, email, pictureURL);

					// UserDataFacade call to store user info
					if (userDataFacade.insertUser(user) == 1) {
						return userDataFacade.selectUserById(userId);
					} else {
						return null;
					}

				} // end user duplication check if
			} else {
				logger.info("[login]", "Email is not verified: " + email);
				return null;
			} // end email verifying check if
		} else {
			logger.info("[login]", "Invalid ID token: " + idTokenString);
			return null;
		} // end token check if
	} // end login method

	// It is need to search user function using user's email
	// So, return back getUserByEmail function that was deprecated.
	@Override
	public UserModel getUserByEmail(String email) {
		return userDataFacade.selectUserByEmail(email);
	}
}
