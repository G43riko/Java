#version 130

const int MAX_LIGHTS = 4;

const float density = 0.02;
const float gradient = 1.5;

in vec3 position;
in vec2 textureCoords;
in vec3 normal;

out float distance;
out float visibility;
out vec2 pass_textureCoords;
out vec3 surfaceNormal;
out vec3 toLightVector[MAX_LIGHTS];
out vec3 toCameraVector;



uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 eyePos;
uniform vec3 lightPosition[MAX_LIGHTS];
uniform int fog;
uniform int fakeLight;

void main(){
	vec4 worldPosition = transformationMatrix * vec4(position,1);
	vec4 positionRelativeToCamera = viewMatrix * worldPosition;
	
	gl_Position = projectionMatrix * positionRelativeToCamera;
	
	pass_textureCoords = textureCoords;
	
	vec3 actNormal = normal;
	
	if(fakeLight == 1){
		actNormal = vec3(0.0, 1.0, 0.0);
	}
	
	surfaceNormal = (transformationMatrix * vec4(actNormal,0.0)).xyz;
	for(int i=0 ; i<MAX_LIGHTS ; i++){
		toLightVector[i] = lightPosition[i] - worldPosition.xyz;
	}
	toCameraVector = ((viewMatrix) * vec4(0,0,0,1)).xyz - worldPosition.xyz;
	distance = length(eyePos - worldPosition.xyz);
	if(fog == 1){ 
		visibility = exp(-pow((distance * density), gradient));
		visibility = clamp(visibility,0.0,1.0);
	}
}