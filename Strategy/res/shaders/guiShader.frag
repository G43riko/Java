#version 130

in vec2 pass_textureCoords;

out vec4 out_Color;

uniform sampler2D textureSampler;


void main(){
	out_Color =  texture(textureSampler, pass_textureCoords);
	out_Color = vec4(1,0,0,1);
}