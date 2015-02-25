#version 120

varying vec2 texCoord0;

uniform vec3 R_ambient;
uniform vec3 R_color;
uniform sampler2D diffuse;
uniform int O_texturing;


void main(){
	if(O_texturing == 1)
		gl_FragColor = texture2D(diffuse,texCoord0.xy) * vec4(R_ambient,1);
	else
		gl_FragColor = vec4(R_color, 1) * vec4(R_ambient,1);
}