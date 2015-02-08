#version 400 core

in vec3 position;
in vec2 textureCoords;

out float distance;
out vec2 pass_textureCoords;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 eyePos;

void main(){
	vec4 worldPosition = transformationMatrix * vec4(position,1);
	gl_Position = projectionMatrix * viewMatrix * worldPosition;
	distance = length(eyePos - worldPosition.xyz);
	pass_textureCoords = textureCoords;
	
}