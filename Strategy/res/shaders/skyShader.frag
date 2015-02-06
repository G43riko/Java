#version 400 core

in vec2 pass_textureCoords;

out vec4 out_Color;

struct DirectionalLight{
    vec3 color;
    float intensity;
    vec3 direction;
};

uniform DirectionalLight directionalLight;
uniform sampler2D textureSampler;
uniform int view;
uniform vec3 color;
uniform vec3 ambient;

void main(){
	
	if(view == 0){
		out_Color = vec4(ambient,1) * texture(textureSampler, vec2(pass_textureCoords.x,pass_textureCoords.y+1));
	}
	else if(view == 1){
		out_Color = 1-texture(textureSampler, pass_textureCoords);;
	}
	else if(view == 2){
		vec4 text = texture(textureSampler, pass_textureCoords);
		float average = (text.x + text.y + text.z)/3; 
		out_Color = vec4(average, average, average,1);
	}
	else if(view == 3){
		if(color != 0){
			out_Color = vec4(color,1);
		}
		else
			out_Color = vec4(0.5, 0.5, 0.5, 1);
	}
}