#version 400 core

in vec2 pass_textureCoords;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec3 color;

uniform vec3 ambient;

void main(){
	out_Color = vec4(ambient,0.5) * texture(textureSampler, pass_textureCoords);// * vec4(0.9,0.1,0.9,1);
}