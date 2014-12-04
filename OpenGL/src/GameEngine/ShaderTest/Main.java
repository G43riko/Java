package GameEngine.ShaderTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Main {

	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Teddst");
			Display.create();
		} catch (LWJGLException e) {
			System.out.println("display nebol inicializovany");
			Display.destroy();
			System.exit(1);
		}
		
		int shaderProgram = glCreateProgram();
		int vertexShader = Main.addShader(GL_VERTEX_SHADER, "shader.vert");
		int fragmentShader = Main.addShader(GL_FRAGMENT_SHADER, "shader.frag");
		
		glAttachShader(shaderProgram,vertexShader );
		glAttachShader(shaderProgram,fragmentShader );
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
		
		while(!Display.isCloseRequested()){
			
			glUseProgram(shaderProgram);
			
			glBegin(GL_TRIANGLES);
			{
				glColor3f(1,0,0);	glVertex2f(-0.5f, -0.5f);
				glColor3f(0,1,0);	glVertex2f(0.5f , -0.5f);
				glColor3f(0,0,1);	glVertex2f(0    , 0.5f );
			}
			glEnd();
			glUseProgram(0);
			Display.update();
			Display.sync(60);
		}
		glDeleteProgram(shaderProgram);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		Display.destroy();
		System.exit(0);
	}
	
	private static int addShader(int type, String file){
		int shader = glCreateShader(type);
		StringBuilder source  = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(new FileReader("src/GameEngine/ShaderTest/"+file));
			String line;
			while((line = reader.readLine())!=null){
				source.append(line);
			}
			reader.close();
		}catch(IOException e){
			System.out.println("VERTEX shader nejde naËÌtaù");
			Display.destroy();
			System.exit(1);
		}
		
		glShaderSource(shader,source);
		glCompileShader(shader);
		if(glGetShaderi(shader,GL_COMPILE_STATUS)==GL_FALSE){
			System.out.println("shader nebol skompilovany");
			Display.destroy();
			System.exit(1);
		}
		
		return shader;
	}

}
