package servlets;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import beans.session.loginInfo;
import entities.User;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//System.getenv("JAVA_TOP") 

/* Meant to accept login from discord redirect
 * Then extract what is useful userdata
 */

@WebServlet("/login")
public class login extends HttpServlet {
	
	@Inject loginInfo info;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		//access token and refresh token
		//MOck test
		User p = new User();
		p = mock();
		info.saveToSession(p);
		
		System.out.println("Right before redirect");
		res.sendRedirect("index.xhtml");
		//-----------------------------------------
		/*
		String code = req.getParameter("code");
		String clientRedirectUrl = req.getRequestURL().toString();
		//if they type the login url directly, send them to discord auth. 
		if(code==null) {
			//get these from web.xml
			String loginUrl = getServletContext().getInitParameter("discord_login_url");
			System.out.println("This is redirected url now " + clientRedirectUrl);
			String clientID = getServletContext().getInitParameter("CLIENT_ID");
			
			String discordBot = loginUrl+"?client_id="+clientID+"&redirect_uri="+clientRedirectUrl+"&response_type=code&scope=identify%20guilds";
			res.sendRedirect(discordBot);
			return;
		}
		
		//Get accesstoken and use it to get user
		User u = null;
		try {
			String accesstoken = get_acess_token(code, clientRedirectUrl);
			u = get_user(accesstoken);
		} catch (JSONException|InterruptedException|IOException e) {
			e.printStackTrace();
		}
		
		//saveToDatabase(u);
		//saveToSession(u, req);
		
		//MOck test
		u = mock();
		info.saveToSession(u);
		
		System.out.println("Right before redirect");
		res.sendRedirect("index.xhtml");
		*/
	}
	
	private User mock() {
		User u = new User();
		u.setAdmin(true);
		u.setAvatar("5555");
		u.setDiscord_id("33333333");
		u.setDiscord_nickname("Funujin");
		return u;
	}
	
	
	
	private void saveToDatabase(User u) {
		User user = new User();
		//Connecting to db might be different
		//DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		//UserDAO userDao = dao.getUserDAO();
		try {
			//user = userDao.getUser(u.getDiscord_id());
			if(user.isAdmin()) {
				u.setAdmin(true);
			}else {
				u.setAdmin(false);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//user not found, insert it into db
		if(user==null) {
			try {
				u.setAdmin(false);
				//userDao.addUser(u);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void saveToSession(User u, HttpServletRequest req) {
		//I should save it to a session bean
		
		
		HttpSession session = req.getSession();
		session.setAttribute("nickname", u.getDiscord_nickname());
		session.setAttribute("avatar", u.getAvatar());
		if(u.isAdmin())session.setAttribute("isAdmin", "true");
		else session.setAttribute("isAdmin", "false");
		session.setAttribute("discord_id", u.getDiscord_id());
	}
	
	
	//Use discord REST api to get info about the discord user that logged in using their token
	private User get_user(String access_token) throws IOException, InterruptedException {
		User bruker = new User();
		
		String url = getServletContext().getInitParameter("discord_user_info");
		
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(URI.create(url))
				.header("Authorization", "Bearer "+access_token)
				.build();
		
		var client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.statusCode());
		System.out.println(response.body());
		
		TreeMap<String, String> tree = new TreeMap<>();
		String[] str = response.body().substring(0, response.body().length()-1).split(",");
		for(int i = 0; i < str.length; i++) {
			int p = str[i].indexOf(':', 0);
			System.out.println(str[i].substring(2, p-1));
			System.out.println(str[i].substring(p+2, str[i].length()));
			tree.put(str[i].substring(2, p-1), str[i].substring(p+2, str[i].length()));
		}
		
		String discord_id = tree.get("id");
		bruker.setDiscord_id(discord_id.substring(1, discord_id.length()-1));
		String nickname = tree.get("username");
		bruker.setDiscord_nickname(nickname.substring(1, nickname.length()-1));
		String avatar = tree.get("avatar");
		bruker.setAvatar(avatar.substring(1, avatar.length()-1));
		
		System.out.println(bruker);
		return bruker;
	}
	
	
	//Use discord REST api to find the accesstoken of the person logging in
	private String get_acess_token(String code, String redirect_uri) throws IOException, InterruptedException, JSONException {
		String tokenurl = getServletContext().getInitParameter("discord_token_url");
		String client_id = getServletContext().getInitParameter("CLIENT_ID");
		String client_secret = getServletContext().getInitParameter("CLIENT_SECRET");
		//the code
		Map<Object, Object> data = new HashMap<>();
		data.put("client_id", client_id);
		data.put("client_secret", client_secret);
		data.put("grant_type", "authorization_code");
		data.put("code", code);
		data.put("redirect_uri", redirect_uri);
		
	
		HttpRequest request = HttpRequest.newBuilder()
				.POST(buildFormDataFromMap(data))
				.uri(URI.create(tokenurl))
				.header("Content-Type", "application/x-www-form-urlencoded")
				.build();
		var client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		System.out.println(response.statusCode());
		System.out.println(response.body());
		
		JSONObject obj = new JSONObject(response.body());
		String s = (String) obj.get("access_token");
		System.out.println(s);
	
		return s;
	}
	
	private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        System.out.println(builder.toString());
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
	
	

}
