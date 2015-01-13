#version 120
#include "lighting.glh"

varying vec2 texCoord0;
varying mat3 tbnMatrix;
varying vec3 worldPos0;

uniform sampler2D diffuse;
uniform sampler2D normalMap;

uniform DirectionalLight R_directionalLight;

void main(){
	vec3 normal = tbnMatrix * (255.0/128.0 * texture2D(normalMap,texCoord0.xy).xyz - 1);
	gl_FragColor = texture2D(diffuse, texCoord0.xy) * calcDirectionalLight(R_directionalLight, normalize(normal),worldPos0);
    
}