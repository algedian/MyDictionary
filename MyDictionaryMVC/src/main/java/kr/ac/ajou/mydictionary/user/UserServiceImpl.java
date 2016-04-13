package kr.ac.ajou.mydictionary.user;

import java.util.Arrays;

import javax.annotation.Resource;

import kr.ac.ajou.mydictionary.userdata.UserDataFacade;

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

@Service("userService")
public class UserServiceImpl implements UserService {
	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource(name = "userDataBaseFacade")
	private UserDataFacade userDataFacade;

	/** Global instance of the HTTP transport. */
	private static final String CLIENT_ID = "768889397569-ifca7s46aplo8i4ikt95ba6ihjmmfdlf.apps.googleusercontent.com";

	/** Global instance of the HTTP transport. */
	private static HttpTransport httpTransport;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	public UserServiceImpl() {
		super();
	}

	/**
	 * 구글 author 하고, mysql db에 사용자 정보 있는지 확인하고 성공 & 실패 그래서 아마도 parameter는 받을 필요
	 * 없을거 같은뎅??
	 * 
	 * @exception 로그인
	 *                실패
	 */

	@Override
	public boolean login(String idTokenString) throws Exception {
		
		httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JSON_FACTORY)
				.setAudience(Arrays.asList(CLIENT_ID))
				//.setIssuer("https://accounts.google.com").build();
				.setIssuer("accounts.google.com").build();
		
		GoogleIdToken idToken = verifier.verify(idTokenString);
		
		System.err.println(idTokenString+" => "+idToken);
		
		if (idToken != null) {
			Payload payload = idToken.getPayload();

			// Print user identifier
			String userId = payload.getSubject();
			System.out.println("User ID: " + userId);

			// Get profile information from payload
			String email = payload.getEmail();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			
			System.err.println("userID:"+userId+" email:"+email+" verified:"+emailVerified+" name:"+name);
			
			if(emailVerified) {
				System.err.println("userID:"+userId+" email:"+email+" verified:"+emailVerified+" name:"+name);
				// UserDataFacade call to store user info
				return true;	
			}
		}
		
		System.err.println("Invalid ID token.");
		return false;
	}

	@Override
	public void loginTest(String userId) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
