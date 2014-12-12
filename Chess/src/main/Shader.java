package main;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL20;

public class Shader {
	private int shaderProgram;
	private int vertexShader;
	private int fragmentShader;
	
	public Shader(String filename) {
		shaderProgram = glCreateProgram();
		
		vertexShader = Utils.shaderLoader(GL_VERTEX_SHADER, (filename+".vert"));
		fragmentShader = Utils.shaderLoader(GL_FRAGMENT_SHADER, filename+".frag");
		
		glAttachShader(shaderProgram,vertexShader );
		glAttachShader(shaderProgram,fragmentShader );
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
	}
	
	public int getShaderID(){
		return this.shaderProgram;
	}
	
	public void cleanUp(){
		GL20.glDetachShader(shaderProgram, vertexShader);
		GL20.glDetachShader(shaderProgram, fragmentShader);
		glDeleteProgram(shaderProgram);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		//Display.destroy();
	}

}
