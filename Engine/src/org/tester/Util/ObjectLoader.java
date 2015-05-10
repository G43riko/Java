package org.tester.Util;

import glib.util.vector.GVector3f;

import org.engine.object.GameObject;
import org.engine.rendering.material.Material;
import org.engine.rendering.material.Texture2D;
import org.engine.rendering.model.Model;
import org.engine.utils.OBJLoader;

public class ObjectLoader {
	public static GameObject loadModel(String name){
		
		Model model = OBJLoader.loadObjModel(name);
		Texture2D t = new Texture2D("materials/"+name+"_specular.jpg");
		Material material = new Material(new Texture2D("materials/"+name+".jpg"), t);
		
		GameObject object = new GameObject(material, model);
		
		object.setScale(new GVector3f(0.1f));
		
		return object;
	}
}
