package beans.interfaces;

import entities.User;

public interface SessionUserInfo {
//	HttpSession session = req.getSession();
//	session.setAttribute("nickname", u.getDiscord_nickname());
//	session.setAttribute("avatar", u.getAvatar());
//	if(u.isAdmin())session.setAttribute("isAdmin", "true");
//	else session.setAttribute("isAdmin", "false");
//	session.setAttribute("discord_id", u.getDiscord_id());
//}
	public abstract void saveToSession(User user);
	
	
	
}
