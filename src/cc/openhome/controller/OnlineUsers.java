package cc.openhome.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineUsers implements HttpSessionListener{
	public static int counter;
	//開新瀏覽器時會+1 登出不會-1 登出在登入也不會做增減
	//在logout.java雖有寫session.invalidate() 但counter最終沒變動 大概是destroyed後馬上有自動created了
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		OnlineUsers.counter+=1;
		System.out.println("created "+OnlineUsers.counter);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		OnlineUsers.counter-=1;
		System.out.println("Destroyed "+OnlineUsers.counter);
	}
}
