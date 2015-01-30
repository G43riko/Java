#version 120
#include "lighting.glh"

varying vec2 texCoord0;
varying vec3 worldPos0;
varying vec3 normal0;
uniform vec3 R_color;
uniform int O_texturing;

uniform sampler2D diffuse;

uniform PointLight R_pointLight;

void main(){
	if(O_texturing == 0)
		gl_FragColor = texture2D(diffuse, texCoord0.xy) * calcPointLight(R_pointLight, normalize(normal0),worldPos0);
	else
		gl_FragColor = vec4(R_color*0.5,1) * calcPointLight(R_pointLight, normalize(normal0),worldPos0);
    
}   