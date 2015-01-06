#version 120
#include "lighting.glh"
varying vec2 texCoord0;
varying vec3 normal0;
varying vec3 worldPos0;

uniform vec3 baseColor;
uniform vec3 ambientLight;
uniform sampler2D diffuse;

uniform DirectionalLight directionalLight;

void main(){
	gl_FragColor = texture2D(diffuse, texCoord0.xy) * calcDirectionalLight(directionalLight, normalize(normal0),worldPos0);
    
}   