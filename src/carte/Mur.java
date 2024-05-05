package carte;

public class Mur {

	public Obstacle position;

	public Mur(Obstacle position) {
		this.position = position;
	}

	public Obstacle getPosition() {
		return position;
	}

	public void setPosition(Obstacle position) {
		this.position = position;
	}

}
