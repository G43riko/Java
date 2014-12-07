package EngineTester;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import Shaders.StaticShader;
import Terrains.Terrain;
import Textures.ModelTexture;
import Textures.TerrainTexture;
import Textures.TerrainTexturePack;
import Entities.BaseParticle;
import Entities.Camera;
import Entities.Entity;
import Entities.Light;
import Levels.LevelLoader;
import Models.RawModel;
import Models.TexturedModel;
import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.MasterRenderer;
import RenderEngine.OBJLoader;
import RenderEngine.EntityRenderer;

public class MainGameLoop {

	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy2.png"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud.png"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers.png"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path.png"));

		TerrainTexturePack  texturePack = new TerrainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap.png"));
		
		
		List<Entity> scene = new ArrayList<Entity>();
		List<Terrain> terrains = new ArrayList<Terrain>();
		List<BaseParticle> particles = new ArrayList<BaseParticle>();
		
		System.out.println( GL11.glGetString(GL11.GL_VERSION));
		
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("stallTexture.png")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setRefectivity(1);
		Entity entity = new Entity(staticModel,	new Vector3f(0,0,-25),0,0,0,1);
		scene.add(entity);
		
		model = OBJLoader.loadObjModel("grassModel", loader);
		staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("grassTexture.png")));
		texture = staticModel.getTexture();
		
		
		for(int i=0 ; i<2400 ; i++){
			float x = (float)Math.random()*1600-800;
			float z = -(float)Math.random()*800;
			float rot = (float)Math.random()*360;
			Entity entity2 = new Entity(staticModel,new Vector3f(x,0,z),0,rot,0,1);
			entity2.getModel().getTexture().setHasTransparency(true);
			entity2.getModel().getTexture().setUseFakeLighting(true);
			scene.add(entity2);
		}
		
		model = OBJLoader.loadObjModel("lowPolyTree", loader);
		staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("lowPolyTree.png")));
		texture = staticModel.getTexture();
		
		for(int i=0 ; i<800 ; i++){
			float x = (float)Math.random()*1600-800;
			float z = -(float)Math.random()*800;
			float rot = (float)Math.random()*360;
			float scale = (float)Math.random()/1f+0.8f;
			Entity entity2 = new Entity(staticModel,new Vector3f(x,0,z),0,rot,0,scale);
			scene.add(entity2);
		}
		
		model = OBJLoader.loadObjModel("fern", loader);
		staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("fern.png")));
		texture = staticModel.getTexture();
		
		for(int i=0 ; i<1200 ; i++){
			float x = (float)Math.random()*1600-800;
			float z = -(float)Math.random()*800;
			float rot = (float)Math.random()*360;
			float scale = (float)Math.random()/1f+0.8f;
			Entity entity2 = new Entity(staticModel,new Vector3f(x,0,z),0,rot,0,scale);
			entity2.getModel().getTexture().setHasTransparency(true);
			scene.add(entity2);
		}
		
		model = OBJLoader.loadObjModel("planeRot2", loader);
		staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("particle.png")));
		texture = staticModel.getTexture();
		BaseParticle entity3 = new BaseParticle(staticModel,new Vector3f(0,2,0),0.1f);
		entity3.getModel().getTexture().setHasTransparency(true);
		entity3.getModel().getTexture().setUseFakeLighting(true);
		particles.add(entity3);
		
		
		Terrain terrain = new Terrain(0,-1,loader,texturePack,blendMap,"heightMap");
		Terrain terrain2 = new Terrain(-1,-1,loader,texturePack,blendMap,"heightMap");
		terrains.add(terrain);
		terrains.add(terrain2);
		
		LevelLoader.loadLevel("level1",loader,scene,terrains);
		
		Light light = new Light(new Vector3f(3000,2000,2000),new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()){
			entity.increaseRotation(0,1,0);
			camera.move();
			
			for(Terrain terren:terrains){
				renderer.proccessTerrain(terren);
			}
			
			for(Entity obj:scene){
				renderer.proccessEntity(obj);
			}
			for(BaseParticle obj:particles){
				double triangleX =(camera.getPosition().getX()-obj.getPosition().getX());
				double triangleY =(camera.getPosition().getY()-obj.getPosition().getY());
				double triangleZ =(camera.getPosition().getZ()-obj.getPosition().getZ());
//				double triangleX =(obj.getPosition().getX()-camera.getPosition().getX());
//				double triangleY =(obj.getPosition().getY()-camera.getPosition().getY());
//				double triangleZ =(obj.getPosition().getZ()-camera.getPosition().getZ());
				double Y = (Math.atan2(triangleZ, triangleX));
				double X = (Math.atan2(triangleY, triangleZ));
				double Z = (Math.atan2(triangleX, triangleY));
				
				obj.setRotY((float)Math.toDegrees(-Y));
				//obj.setRotZ((float)Math.toDegrees(-Z));
				
//				System.out.println("horizontal je: "+obj.getHoriz()+" a vertikal je "+obj.getVert());
//				System.out.println("teta je: "+obj.teta+" a fi je "+obj.fi);
//				System.out.println(obj.getRotX()+" "+obj.getRotY()+" "+obj.getRotZ());
				
				//obj.increaseRotation(0, 0, Y);
				renderer.proccessEntity(obj);
			}
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
