package tests.vbo;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

import org.lwjgl.opengl.Display;

import utils.FileLoader;

public class BasicShader {
	private int shaderProgram;
	private int vertexShader;
	private int fragmentShader;
	
	public BasicShader(String filename) {
		shaderProgram = glCreateProgram();
		
		vertexShader = FileLoader.shaderLoader(GL_VERTEX_SHADER, (filename+".vert"));
		fragmentShader = FileLoader.shaderLoader(GL_FRAGMENT_SHADER, filename+".frag");
		
		glAttachShader(shaderProgram,vertexShader );
		glAttachShader(shaderProgram,fragmentShader );
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
	}
	
	public int getShaderID(){
		return this.shaderProgram;
	}
	
	public void cleanUp(){
		glDeleteProgram(shaderProgram);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		Display.destroy();
	}
}
