#version 400 core

const int MAX_LIGHTS = 4;

in float distance;

out vec4 out_Color;

void main(){
	float value = 100;
	out_Color = vec4(distance/value,distance/value,distance/value,1);
}