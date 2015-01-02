package GameEngine.core;

import java.util.ArrayList;

import javax.swing.JButton;





import GameEngine.components.BaseLight;
import GameEngine.components.DirectionalLight;
import GameEngine.components.PointLight;
import GameEngine.components.SpotLight;
//import GameEngine.core.Options;
import GameEngine.rendering.Camera;
import GameEngine.rendering.Material;
import GameEngine.rendering.Mesh;
import GameEngine.rendering.Vertex;

 
public abstract class Game {
	private GameObject root; 
	
	public void init(){
		
	};
	
	public void input(float delta){
		getRootObject().input(delta);
	};
	
	public void update(float delta){
		getRootObject().update(delta);
	};
	
	
	public GameObject getRootObject(){
		if(root == null){
			root = new GameObject();
		}
		return root;
	}
}

