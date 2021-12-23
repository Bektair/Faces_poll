package beans.session;
import jakarta.inject.Named;

import java.io.Serializable;

import beans.interfaces.SessionUserInfo;
import entities.User;
import jakarta.enterprise.context.SessionScoped;

@Named
@jakarta.enterprise.context.SessionScoped
public class loginInfo implements Serializable, SessionUserInfo {

	private String discord_id;
	boolean isAdmin;
	String discord_nickname;
	String avatar;

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getDiscord_nickname() {
		return discord_nickname;
	}

	public void setDiscord_nickname(String discord_nickname) {
		this.discord_nickname = discord_nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public loginInfo() {
		
	}
	
	public String getDiscord_id() {
		return discord_id;
	}

	public void setDiscord_id(String discord_id) {
		this.discord_id = discord_id;
	}

	@Override
	public void saveToSession(User user) {
		setDiscord_id(user.getDiscord_id());
		setAdmin(user.isAdmin());
		setAvatar(user.getAvatar());
		setDiscord_nickname(user.getDiscord_nickname());
		
	}
	
	
	
	
	
}
