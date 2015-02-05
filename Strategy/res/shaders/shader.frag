#version 400 core

in vec2 pass_textureCoords;

out vec4 out_Color;

uniform sampler2D textureSampler;

uniform int view;
uniform int select;
uniform vec3 color;
uniform vec3 ambient;
uniform int blur;
uniform vec2 mouseDir;

void main(){
	out_Color = vec4(ambient,1) * texture(textureSampler, pass_textureCoords);
}