package Chat.own;

public class Chat {
	View view = new View(this);
	
	public Chat(){
		view.showLoginView();
	}

	public void start(String login, String ip, String port, boolean isHost) {
		view.showChatView(login);
	}

	public void stop() {
		view.showLoginView();
	}
}
