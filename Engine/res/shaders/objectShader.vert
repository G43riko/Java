#version 130

in vec3 position;
in vec2 textureCoords;

out vec2 pass_textureCoords;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main(){
	vec4 worldPosition = transformationMatrix * vec4(position,1);
	vec4 positionRelativeToCamera = viewMatrix * worldPosition;
	
	gl_Position = projectionMatrix * positionRelativeToCamera;
	
	pass_textureCoords = textureCoords;
}