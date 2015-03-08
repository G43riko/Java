#version 400 core

const int MAX_LIGHTS = 4;

in vec3 position;
in vec2 textureCoords;
in vec3 normal;

out float distance;
out vec2 pass_textureCoords;
out vec3 surfaceNormal;
out vec3 toLightVector[MAX_LIGHTS];
out vec3 toCameraVector;


uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 eyePos;
uniform vec3 lightPosition[MAX_LIGHTS];

void main(){
	vec4 worldPosition = transformationMatrix * vec4(position,1);
	gl_Position = projectionMatrix * viewMatrix * worldPosition;
	pass_textureCoords = textureCoords;
	surfaceNormal = (transformationMatrix * vec4(normal,0.0)).xyz;
	for(int i=0 ; i<MAX_LIGHTS ; i++){
		toLightVector[i] = lightPosition[i] - worldPosition.xyz;
	}
	toCameraVector = (inverse(viewMatrix) * vec4(0,0,0,1)).xyz - worldPosition.xyz;
	distance = length(eyePos - worldPosition.xyz);
}