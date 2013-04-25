package bucket.game.client.util;

/**
 * This will be used by the client to determine how to colour code items on maps for the player.
 * 
 * @author Katharina
 * 
 */
public class Ally {

	private enum alliance {
		PLAYER, NEUTRAL, HOSTILE, FRIENDLY, UNKNOWN;
	}

	private alliance a;

	/**
	 * Will set the enum to @UNKNOWN which might occur in strange cases where the player has been sent data by the server
	 * that he/she wasn't supposed to get.
	 */
	public Ally() {
		a = alliance.UNKNOWN;
	}

	public void setFriendly() {
		a = alliance.FRIENDLY;
	}

	public void setHostile() {
		a = alliance.HOSTILE;
	}

	public void setNeutral() {
		a = alliance.NEUTRAL;
	}

	public void setPlayer() {
		a = alliance.PLAYER;
	}

	public alliance getAlliance() {
		return a;
	}

}
