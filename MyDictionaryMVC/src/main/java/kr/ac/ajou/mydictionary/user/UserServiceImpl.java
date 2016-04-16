package kr.ac.ajou.mydictionary.user;

import java.util.Arrays;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.userdata.UserDataFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
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
	
	private UserModel currentUser;

	public UserServiceImpl() {
		super();
	}

	@Override
	public boolean login(String idTokenString) throws Exception {

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

			if (emailVerified) {

				if ((currentUser = userDataFacade.selectUserById(userId)) != null)
					return true;// login success
				else {
					// 일단은 초기값으로 인덱스를 -1을 넣어놓고... 파사드에서는 insert 시 id는 안읽으니까
					// 상관없을듯합니당.
					UserModel user = new UserModel(-1, userId, name, email, pictureURL);

					// UserDataFacade call to store user info
					if (userDataFacade.insertUser(user) == 1) {
						currentUser = userDataFacade.selectUserByEmail(email);
						return true;
					} else {
						return false;
					}

				} // end user dup check if
			} else {
				System.err.println("Email is not verified: " + email);
				return false;
			} // end email verifying check if
		} else {
			System.err.println("Invalid ID token: " + idTokenString);
			return false;
		} // end token check if
	} // end login method

	/*
	 * 사용자를 email로 검색하는 것은 email로 user를 select 해오는 것이 필요하다고 생각되옵니다. 그래서
	 * deprecated 된 selectUserByEmail을 부활시켰사옵니다.
	 */
	@Override
	public UserModel getUserByEmail(String email) {
		return userDataFacade.selectUserByEmail(email);
	}
	
	@Override
	public UserModel getCurrentUser() {
		return currentUser;
	}
}
