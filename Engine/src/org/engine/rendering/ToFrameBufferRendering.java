package org.engine.rendering;

import java.nio.ByteBuffer;

import glib.util.vector.GVector2f;

import org.engine.rendering.material.Texture2D;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.EXTFramebufferObject.*;

public class ToFrameBufferRendering {
	private Texture2D texture;
	private int framebufferID;
	private int depthRenderBufferID;

	public ToFrameBufferRendering(){
		this(new GVector2f(256, 256));
	}
	
	public ToFrameBufferRendering(GVector2f resolution){
		if (!GLContext.getCapabilities().GL_EXT_framebuffer_object) {
			System.out.println("FBO nieje podporovaný!!!");
			System.exit(0);
		}
		else {
			framebufferID = glGenFramebuffers();
			

			
			glBindFramebuffer(GL_FRAMEBUFFER, framebufferID);


			texture = initTexture(resolution);
//			texture = createDepthTextureAttachment(resolution);

			depthRenderBufferID = initDepthBuffer(resolution);
			
			glBindFramebuffer(GL_FRAMEBUFFER, 0);
		}
	}
	
	private int initDepthBuffer(GVector2f resolution){
		int depthBuffer = glGenRenderbuffers();
		
		glBindRenderbuffer(GL_RENDERBUFFER, depthBuffer);
		glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT24, resolution.getXi(), resolution.getYi());
		glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, depthBuffer);
		
		return depthBuffer;

	}
	
	private Texture2D initTexture(GVector2f resolution){
		Texture2D txt = new Texture2D("FBO", glGenTextures(), resolution);
		
		glBindTexture(GL_TEXTURE_2D, txt.getId());
		
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, resolution.getXi(), resolution.getYi(), 0, GL_RGBA, GL_INT, (ByteBuffer) null);
		glFramebufferTexture(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, txt.getId(), 0);
		return txt;
	}
	
	private Texture2D createDepthTextureAttachment(GVector2f resolution){
		Texture2D txt = new Texture2D("FBO", glGenTextures(), resolution);
		
        glBindTexture(GL_TEXTURE_2D, txt.getId());
        
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        
        glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT32, resolution.getXi(), resolution.getYi(), 0, GL_DEPTH_COMPONENT, GL_FLOAT, (ByteBuffer) null);
		glFramebufferTexture(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, txt.getId(), 0);
        return txt;
    }
	
	public void startRenderToFrameBuffer(){
		glDepthFunc (GL_LEQUAL);
		glEnable (GL_DEPTH_TEST);
		glShadeModel (GL_SMOOTH);
		glHint (GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 

		glViewport (0, 0, texture.getSize().getXi(), texture.getSize().getYi());
	
		glBindTexture(GL_TEXTURE_2D, 0);
		
		glBindFramebuffer(GL_FRAMEBUFFER, framebufferID);
//		glBindFramebuffer(GL_FRAMEBUFFER, depthRenderBufferID);

		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void stopRenderToFrameBuffer(){
//		glEnable(GL_TEXTURE_2D);
//		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
		glViewport (0, 0, Display.getWidth(), Display.getHeight());
	}
	
	public void cleanUp() {//call when closing the game
    	GL30.glDeleteFramebuffers(framebufferID);
        GL11.glDeleteTextures(texture.getId());
        GL30.glDeleteRenderbuffers(depthRenderBufferID);
    }
	
//	public ToFrameBufferRendering(GVector2f resolution){
//		if (!GLContext.getCapabilities().GL_EXT_framebuffer_object) {
//			System.out.println("FBO nieje podporovaný!!!");
//			System.exit(0);
//		}
//		else {
//			framebufferID = glGenFramebuffersEXT();
//			texture = new Texture2D("FBO", glGenTextures(), resolution);
//			depthRenderBufferID = glGenRenderbuffersEXT();
//
//			glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, framebufferID);
//
//			glBindTexture(GL_TEXTURE_2D, texture.getId());
//			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
////			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
////			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
//			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, resolution.getXi(), resolution.getYi(), 0,GL_RGBA, GL_INT, (java.nio.ByteBuffer) null);
//			glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT,GL_COLOR_ATTACHMENT0_EXT,GL_TEXTURE_2D, texture.getId(), 0);
//
//
//			glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, depthRenderBufferID);
//			glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL14.GL_DEPTH_COMPONENT24, resolution.getXi(), resolution.getYi());
//			glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT,GL_DEPTH_ATTACHMENT_EXT,GL_RENDERBUFFER_EXT, depthRenderBufferID);
//
//			glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
//		}
//	}
//	
//	public void startRenderToFrameBuffer(){
//		glDepthFunc (GL_LEQUAL);
//		glEnable (GL_DEPTH_TEST);
//		glShadeModel (GL_SMOOTH);
//		glHint (GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 
//
//		glViewport (0, 0, texture.getSize().getXi(), texture.getSize().getYi());
//	
//		glBindTexture(GL_TEXTURE_2D, 0);
//		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, framebufferID);
//
//		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//	}
//	
//	public void stopRenderToFrameBuffer(){
//		glEnable(GL_TEXTURE_2D);
//		glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);
// 
//		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//		
//		glViewport (0, 0, Display.getWidth(), Display.getHeight());
//	}

	public Texture2D getTexture() {
		return texture;
	}
}
