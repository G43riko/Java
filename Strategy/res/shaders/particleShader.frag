#version 130

in vec2 pass_textureCoords;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec3 color;
uniform float alpha;

uniform vec3 ambient;

void main(){
	float alphaColor = alpha;	
	if(alpha==0)
		alphaColor = 1;
	out_Color = texture(textureSampler, pass_textureCoords) * vec4(color,alphaColor);
}