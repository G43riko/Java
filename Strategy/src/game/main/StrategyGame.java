package game.main;

import org.json.JSONObject;

import game.core.CoreGame;
import game.entity.Bullet;
import game.entity.enemy.BasicEnemy;
import game.entity.player.Player;
import game.light.PointLight;
import game.object.Camera;
import game.object.SkyBox;
import game.particle.ParticleEmmiter;
import game.rendering.RenderingEngine;
import game.rendering.material.Texture2D;
import game.rendering.model.Model;
import game.util.Loader;
import game.world.Block;
import game.world.World;
import glib.util.GLog;
import glib.util.vector.GVector3f;

public class StrategyGame extends CoreGame{
	private static final long serialVersionUID = 1L;
	public static final boolean FLY_MODE = true;
	
	public void init(){
		GLog.sleep(100);
		
		setRenderingEngine(new RenderingEngine());
		setWorld(new World());
//		setWorld(new World("sandBox"));
//		setWorld(new World("mainWorld.gw"));
		setPlayer(new Player(getWorld(), new Camera()));
		setMainCamera(getPlayer().getCamera());
		getWorld().setCamera(getPlayer().getCamera());
		setLoader(new Loader());
		setSkyBox(new SkyBox(getPlayer().getCamera()));
		setSun(new PointLight(new GVector3f(100, 100, 100), new GVector3f(1)));
		setMousePicker(getPlayer());
//		Entity box = new Entity(getBox(1,1,1), new Texture2D("texture.png"));
//		box.setPosition(new GVector3f(0,1,0));
//		addToScene(box);
		
//		Box box = new Box(new GVector3f(0,1,0),1);
//		addToScene(box);
		
//		addToScene(new ParticleEmmiter(new GVector3f(0,1,-5)));
		
//		Tower t = new Tower();
//		t.setTarget(getPlayer());
//		addToScene(t);
		
		BasicEnemy e;
		GVector3f worldSize = getWorld().getMaxSize();
		for(int i=0 ; i<10 ; i++){
			e = new BasicEnemy(getWorld());
			e.setPosition(new GVector3f(Math.random()*(worldSize.getX()-3),2, Math.random()*(worldSize.getZ()-3)));
			e.setTarget(getPlayer());
			e.setBulletColor(new GVector3f(Math.random(), Math.random(), Math.random()));
			addToScene(e);
		}
	
		
	}
}
