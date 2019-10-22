package BlackJack.controller;

public interface CommandObserver {
	void playPressed();
	void hitPressed();
	void standPressed();
	void quitPressed();
}
