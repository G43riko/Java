package org.engine.core;

import java.util.ArrayList;
import java.util.stream.Collectors;

import glib.util.GLog;
import glib.util.vector.GVector3f;

import javax.swing.JFrame;

import org.MainStrategy;
import org.engine.component.Camera;
import org.engine.component.GameComponent;
import org.engine.component.Input;
import org.engine.component.movement.BasicMovement;
import org.engine.component.movement.FPS;
import org.engine.component.movement.TPS;
import org.engine.entity.BasicPlayer;
import org.engine.gui.Gui;
import org.engine.object.GameObjectPhysics;
import org.engine.rendeing.ToFrameBufferRendering;
import org.engine.util.Loader;
import org.engine.util.Maths;
import org.engine.util.MousePicker;
import org.engine.util.OBJLoader;
import org.engine.world.Line;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.strategy.rendering.RenderingEngineStrategy;

public abstract class CoreEngine extends JFrame{
	protected static final long serialVersionUID = 1L;

	private ToFrameBufferRendering frameRender; 
	private Gui gui;
	private Scene scene;
	private RenderingEngineStrategy renderingEngine;

	private static Loader loader = new Loader();
	private Camera camera;
	private boolean running;
	
	
	//CONSTRUCTORS
	
	public CoreEngine(){
		scene = new Scene();
		running = false;
	}
	
	//OTHERS
	
	public abstract void init();
	
	public void createWindow(CoreEngine game){
		gui = Window.createWindow(game);
	};
	
	public void stop(){
		running = false;
	}

	public void cleanUp(){
		running = false;
		renderingEngine.cleanUp();
		Window.cleanUp();
	}

	public void addToScene(GameComponent g){
		scene.add(g);
	}

	public void start(){
		running = true;

		
		if(renderingEngine == null){
			GLog.write("nieje nastavený render engine");
			return;
		}
		
		while(running && !Display.isCloseRequested()){
			input();
			if(gui!=null)
				gui.update();
			update();
			renderingEngine.prepare();
			render();
			Display.update();
			Display.sync(MainStrategy.FPS);
		}
	}
	
	protected void input(){
		tempInput();
		Input.update();
		scene.getScene().stream().forEach(a -> a.input());
	};
	
	protected void update(){
		scene.removeAll(scene.getScene().stream()
										.peek(a->a.update())
										.filter(a->a.isDead())
										.collect(Collectors.toList()));
		if(Display.wasResized()){
			camera.createProjectionMatrix();
			renderingEngine.updateCamera();
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		}
	};
	
	private void finalRender(){
		renderingEngine.renderObject(scene.getObjects());
//		renderingEngine.renderWater(scene.getWaters());
		scene.getWaters().stream().forEach(a -> renderingEngine.renderWater(a));
		
		scene.getOthers().forEach(a -> a.render(renderingEngine));
		
		renderingEngine.renderParticle(scene.getParticles());
	}
	
	protected void render(){

		if(frameRender != null){
			frameRender.startRenderToFrameBuffer();
			renderingEngine.setViewMatrix(Maths.createViewMatrix(new GVector3f(0,5,-20), new GVector3f(0,(System.currentTimeMillis()/10)%360,0)));
		}
		finalRender();

		if(frameRender == null)
			return;
		
		frameRender.stopRenderToFrameBuffer();
		
		renderingEngine.setViewMatrix(Maths.MatrixToGMatrix(Maths.createViewMatrix(camera)));
		finalRender();
		renderingEngine.renderHud(scene.getHuds());
		
	};
	
	//GETTERS

	public RenderingEngineStrategy getRenderingEngine() {
		return renderingEngine;
	}

	public Gui getGui(){
		return gui;
	}

	public Camera getCamera() {
		return camera;
	}

	public boolean isRunning() {
		return running;
	}

	public ArrayList<GameComponent> getScene() {
		return scene.getScene();
	}
	
	public Scene getSceneObject() {
		return scene;
	}
	
	public static Loader getLoader() {
		return loader;
	}
	
	
	//SETTERS
	
	public void setCamera(Camera camera) {
		addToScene(camera);
		this.camera = camera;
		renderingEngine.setMainCamera(camera);
	}
	
	protected void setRenderingEngine(RenderingEngineStrategy renderingEngine) {
		this.renderingEngine = renderingEngine;
	}

	public void setMousePicker(Camera camera){
		camera.setMousePicker( new MousePicker(camera));
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setFrameRender(ToFrameBufferRendering frameRender) {
		this.frameRender = frameRender;
	}

	public ToFrameBufferRendering getFrameRender() {
		return frameRender;
	}

	//TEMPORARY

	public void setMovementType(BasicMovement b) {
		addToScene(b);
		if(b instanceof FPS){
			setCenter(getCamera());
		}
		else if(b instanceof TPS){
			setCenter(((TPS)b).getPlayer());
		}
	}
	
	private GameComponent center; 
	
	private void tempInput(){
		if(Input.getMouseDown(0)){
			GameObjectPhysics o = new GameObjectPhysics(OBJLoader.loadObjModel("sphere", loader));
			o.setPosition(center.getPosition().getInstance());
			if(center instanceof BasicPlayer){
				o.setDirection(((BasicPlayer)center).getForward().mul(-1));
				o.getPosition().addToY(10);
			}
			else if(center instanceof Camera)
				o.setDirection(((Camera)center).getForward().mul(-1));
			addToScene(o);
		}
		if(Input.getMouseDown(1)){
			camera.calcFrustum().stream().forEach(a -> {
				addToScene(new Line(camera.getPosition(), camera.getPosition().add(a),getLoader()));
			});
		}
		
		if(Input.getKeyDown(Keyboard.KEY_H)){
			gui.hideAll();
		}
	}
	
	private void setCenter(GameComponent center) {
		this.center = center;
	}

	
}
