package org.strategy.main;

import org.engine.component.SkyBox;
import org.engine.light.PointLight;
import org.engine.particles.ParticleEmmiter;
import org.engine.rendeing.RenderingEngine;
import org.engine.util.Loader;
import org.json.JSONObject;
import org.strategy.component.CameraStrategy;
import org.strategy.core.CoreGame;
import org.strategy.entity.enemy.BasicEnemy;
import org.strategy.entity.player.Player;
import org.strategy.object.Lamp;
import org.strategy.world.World;

import glib.util.GLog;
import glib.util.vector.GVector3f;

public class StrategyGame extends CoreGame{
	private static final long serialVersionUID = 1L;
	public static final boolean FLY_MODE = true;
	
	public void init(){
		GLog.sleep(100);
		
		setRenderingEngine(new RenderingEngine());
		
//		setWorld(new World());
		setWorld(new World("sandBox"));
//		setWorld(new World("mainWorld.gw"));
		setCamera(new CameraStrategy());
		getCamera().setPosition(getWorld().getMaxSize().div(new GVector3f(2,1,2)));
		setPlayer(new Player(getWorld(), getCamera()));
		getWorld().setCamera(getCamera());
		setLoader(new Loader());
		setSkyBox(new SkyBox(getCamera()));
		setSun(new PointLight(new GVector3f(100, 100, 100), new GVector3f(1)));
		setMousePicker(getCamera());
		
//		addToScene(new ParticleEmmiter(new GVector3f(0,1,-5)));
		
		BasicEnemy e;
		GVector3f worldSize = getWorld().getMaxSize();
		for(int i=0 ; i<1 ; i++){
			e = new BasicEnemy(getWorld());
			e.setPosition(new GVector3f(Math.random()*(worldSize.getX()-3),2, Math.random()*(worldSize.getZ()-3)));
			e.setTarget(getPlayer());
			e.setBulletColor(new GVector3f(Math.random(), Math.random(), Math.random()));
			addToScene(e);
		}
		
		addToScene(new Lamp(new GVector3f(2,1,2),getRenderingEngine()));
	}
}
