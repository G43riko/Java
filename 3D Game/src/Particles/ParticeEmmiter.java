package Particles;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import Textures.ModelTexture;

public class ParticeEmmiter {
	private int maxParticles;
	private List<BasicParticle> particles= new ArrayList<BasicParticle>();

	public ParticeEmmiter(int maxParticles, List<BasicParticle> particles) {
		this.maxParticles = maxParticles;
		this.particles = particles;
		for(int i=0 ; i<maxParticles ; i++){
			particles.add(new BasicParticle(new Vector3f(0,5,0),10,10,300,new ModelTexture(21)));
		}
	}
}
