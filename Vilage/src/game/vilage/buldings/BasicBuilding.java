package game.vilage.buldings;

import game.vilage.resources.ResourceBase;

public class BasicBuilding {
	protected ResourceBase resources;
	
	public void produce(int number){
		if(!resources.canWork(number))
			return;
		resources.build(number);
	}
}
