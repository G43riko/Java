#version 130

in vec3 position;

out float distance;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 eyePos;

void main(){
	vec4 worldPosition = transformationMatrix * vec4(position,1);
	vec4 positionRelativeToCamera = viewMatrix * worldPosition;
	
	gl_Position = projectionMatrix * positionRelativeToCamera;


	//distance = length(eyePos - worldPosition.xyz);
	distance = length((viewMatrix * worldPosition).xyz);

}