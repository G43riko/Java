#version 400 core

in vec3 position;
in vec2 textureCoords;
in vec3 normal;

out vec2 pass_textureCoords;
out vec3 surface;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;


void main(){
	gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position,1);
	pass_textureCoords = textureCoords;
	
	//surface = (transformationMatrix * vec4(normal,1)).xyz;
}