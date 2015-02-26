#version 130

in vec3 position;
in vec2 textureCoords;
in vec3 normal;

out float distance;
out vec2 pass_textureCoords;
out vec3 surface;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 eyePos;

void main(){
	vec4 worldPosition = transformationMatrix * vec4(position,1);
	gl_Position = projectionMatrix * viewMatrix * worldPosition;
	pass_textureCoords = textureCoords;
	//vec3 daco = normal;
	//surface = (worldPosition * vec4(normal,1)).xyz;
	distance = length(eyePos - worldPosition.xyz);
}