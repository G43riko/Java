package org.engine.shadows;


import static org.lwjgl.opengl.EXTFramebufferObject.*;
import static org.lwjgl.opengl.GL11.*;
import glib.util.vector.GVector2f;

import org.engine.rendeing.material.Texture2D;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GLContext;

public class Shadow {
	private Texture2D texture;
	private int framebufferID;
	private int depthRenderBufferID;
	
	private int textureWidth = 256;
	private int textureHeight = 256;
	
	public Shadow(){
		if (!GLContext.getCapabilities().GL_EXT_framebuffer_object) {
            System.out.println("FBO nieje podporovaný!!!");
            System.exit(0);
        }
        else {
			        
	        framebufferID = glGenFramebuffersEXT();
	        texture = new Texture2D("FBO", glGenTextures(), new GVector2f(textureWidth, textureHeight));
//	        colorTextureID = glGenTextures();
	        depthRenderBufferID = glGenRenderbuffersEXT();
	 
	        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, framebufferID);
	 
	        glBindTexture(GL_TEXTURE_2D, texture.getId());
	        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
	        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, textureWidth, textureHeight, 0,GL_RGBA, GL_INT, (java.nio.ByteBuffer) null);
	        glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT,GL_COLOR_ATTACHMENT0_EXT,GL_TEXTURE_2D, texture.getId(), 0);
	 
	 
	        glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, depthRenderBufferID);
	        glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL14.GL_DEPTH_COMPONENT24, textureWidth, textureHeight);
	        glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT,GL_DEPTH_ATTACHMENT_EXT,GL_RENDERBUFFER_EXT, depthRenderBufferID);
	 
	        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
        }
	}
	
	public void startRenderShaderMap(){
		glDepthFunc (GL_LEQUAL);
		glEnable (GL_DEPTH_TEST);
		glShadeModel (GL_SMOOTH);
		glHint (GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 
		
		glViewport (0, 0, textureWidth, textureHeight);
		 
        glBindTexture(GL_TEXTURE_2D, 0);
        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, framebufferID);
        
        glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void stopRenderShaderMap(){
		glEnable(GL_TEXTURE_2D);
        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
 
        
        glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        
        glViewport (0, 0, Display.getWidth(), Display.getHeight());
	}
}
