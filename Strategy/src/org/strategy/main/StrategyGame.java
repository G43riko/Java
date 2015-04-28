package org.strategy.main;

import org.engine.light.PointLight;
import org.engine.util.Loader;
import org.engine.world.SkyBox;
import org.strategy.component.CameraStrategy;
import org.strategy.core.CoreStrategy;
import org.strategy.entity.enemy.BasicEnemy;
import org.strategy.entity.player.Player;
import org.strategy.object.Lamp;
import org.strategy.rendering.RenderingEngineStrategy;
import org.strategy.world.World;

import glib.util.GLog;
import glib.util.vector.GVector3f;

public class StrategyGame extends CoreStrategy{
	private static final long serialVersionUID = 1L;
	public static final boolean FLY_MODE = true;
	
	public void init(){
		GLog.sleep(100);
		
		setRenderingEngine(new RenderingEngineStrategy());
		
//		setWorld(new World());
		setWorld(new World("sandBox"));
		setCamera(new CameraStrategy());
		getCamera().setPosition(getWorld().getMaxSize().div(new GVector3f(2,1,2)));
		setPlayer(new Player(getWorld(), (CameraStrategy)getCamera()));
		getWorld().setCamera((CameraStrategy)getCamera());
		setSkyBox(new SkyBox(getCamera()));
		setSun(new PointLight(new GVector3f(100, 100, 100), new GVector3f(1)));
		setMousePicker(getCamera());
		
//		addToScene(new ParticleEmmiter(new GVector3f(0,1,-5)));
		
		BasicEnemy e;
		GVector3f worldSize = getWorld().getMaxSize();
		for(int i=0 ; i<1 ; i++){
			e = new BasicEnemy(getWorld(), getLoader());
			e.setPosition(new GVector3f(Math.random()*(worldSize.getX()-3),2, Math.random()*(worldSize.getZ()-3)));
			e.setTarget(getPlayer());
			e.setBulletColor(new GVector3f(Math.random(), Math.random(), Math.random()));
			addToScene(e);
		}
		
		addToScene(new Lamp(new GVector3f(2,1,2),getRenderingEngine()));
	}
}
