#version 120
#include "lighting.glh"

varying vec2 texCoord0;
varying vec3 normal0;
varying vec3 worldPos0;

uniform sampler2D diffuse;

uniform SpotLight R_spotLight;

void main(){
	vec3 normal = normalize(normal0);
	
	gl_FragColor = texture2D(diffuse, texCoord0.xy) * calcSpotLight(R_spotLight, normal,worldPos0);
    
}   