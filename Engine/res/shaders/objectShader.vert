#version 130

in vec3 position;
in vec2 textureCoords;
in vec3 normal;

out vec2 pass_textureCoords;

out vec3 surfaceNormal;
out vec3 toCameraVector;
out vec3 worldPos;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

uniform int fakeLight;

void main(){
	vec4 worldPosition = transformationMatrix * vec4(position,1);
	vec4 positionRelativeToCamera = viewMatrix * worldPosition;
	
	gl_Position = projectionMatrix * positionRelativeToCamera;
	
	pass_textureCoords = textureCoords;

	vec3 actNormal = normal;
	if(fakeLight > 0.5){
		actNormal = vec3(0.0, 1.0, 0.0);
	}
	
	worldPos = (transformationMatrix * vec4(position,1.0)).xyz;
	
	surfaceNormal = (transformationMatrix * vec4(actNormal,0.0)).xyz;
}