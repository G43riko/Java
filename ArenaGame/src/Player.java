
public class Player {
	public String name;
	public int money,armor,healt,demage,speed;
	public int wins, losses, score;
	public Player(String meno){
		this.name=meno;
		this.money=10000;
		this.armor=0;
		this.healt=0;
		this.demage=0;
		this.speed=0;
		this.wins=this.losses=this.score=0;
	}
}
