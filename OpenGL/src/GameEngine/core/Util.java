package GameEngine.core;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix3f;

import GameEngine.rendering.Vertex;
 
public class Util {
	public static FloatBuffer createFloatBuffer(int size){
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static ByteBuffer createByteBuffer(int size){
		return BufferUtils.createByteBuffer(size);
	}
	
	public static IntBuffer createIntBuffer(int size){
		return BufferUtils.createIntBuffer(size);
	}
	
	public static IntBuffer createFlippedBuffer(int... values){
		IntBuffer buffer = createIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Vertex[] vertices){
		FloatBuffer buffer = createFloatBuffer(vertices.length*Vertex.SIZE);
		for(int i=0 ; i<vertices.length ; i++){
			buffer.put(vertices[i].getPos().GetX());
			buffer.put(vertices[i].getPos().GetY());
			buffer.put(vertices[i].getPos().GetZ());
			buffer.put(vertices[i].getTexCoord().GetX());
			buffer.put(vertices[i].getTexCoord().GetY());
			buffer.put(vertices[i].getNormal().GetX());
			buffer.put(vertices[i].getNormal().GetY());
			buffer.put(vertices[i].getNormal().GetZ());
		}
		
		buffer.flip();
		
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Matrix4f value){
		FloatBuffer buffer = createFloatBuffer(4*4);
		
		for(int i=0 ; i<4 ; i++){
			for(int j=0 ; j<4 ; j++){
				buffer.put(value.Get(i,j));
			}
		}
		buffer.flip();
		return buffer;
	}
	
	public static String[] removeEmptyString(String[] data){
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0 ; i<data.length ; i++){
			if(!data[i].equals("")){
				result.add(data[i]);
			}
		}
		String[] res = new String[result.size()];
		
		result.toArray(res);
		
		return res;
	}

	public static int[] toIntArray(Integer[] data) {
		int[] result = new int[data.length];
		
		for(int i=0 ; i<data.length ; i++){
			result[i] = data[i].intValue();
		}
		return result;
	}
}
