#version 130

in vec2 textureCoords;

out vec4 out_Color;



void main(){
	out_Color = vec4(textureCoords,0,1);
}