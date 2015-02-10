#version 400 core

in float distance;
in vec2 pass_textureCoords;
in vec3 surface;

out vec4 out_Color;

uniform sampler2D textureSampler;

uniform int view;
uniform int select;
uniform vec3 color;
uniform vec3 ambient;
uniform int blur;
uniform vec2 mouseDir;

vec4 calcBlur(vec2 textureCoords, sampler2D texture, vec2 direction){
	float blurSize = 1.0/256.0;
	vec4 sum = vec4(0.0);
	float hstep = direction.x;
    float vstep = direction.y;
    
	sum += texture(texture, vec2(textureCoords.x - 4 * blurSize * hstep, textureCoords.y - 4.0 * blurSize * vstep)) * 0.05;
	sum += texture(texture, vec2(textureCoords.x - 3 * blurSize * hstep, textureCoords.y - 3.0 * blurSize * vstep)) * 0.09;
	sum += texture(texture, vec2(textureCoords.x - 2 * blurSize * hstep, textureCoords.y - 2.0 * blurSize * vstep)) * 0.12;
	sum += texture(texture, vec2(textureCoords.x - 1 * blurSize * hstep, textureCoords.y - 1.0 * blurSize * vstep)) * 0.15;
	sum += texture(texture, vec2(textureCoords.x , textureCoords.y)) * 0.16;
	sum += texture(texture, vec2(textureCoords.x + 4 * blurSize * hstep, textureCoords.y + 4.0 * blurSize * vstep)) * 0.05;
	sum += texture(texture, vec2(textureCoords.x + 3 * blurSize * hstep, textureCoords.y + 3.0 * blurSize * vstep)) * 0.09;
	sum += texture(texture, vec2(textureCoords.x + 2 * blurSize * hstep, textureCoords.y + 2.0 * blurSize * vstep)) * 0.12;
	sum += texture(texture, vec2(textureCoords.x + 1 * blurSize * hstep, textureCoords.y + 1.0 * blurSize * vstep)) * 0.15;
	
	return sum;
}

void main(){

	if(view == 0){
		if(blur == 0){
			out_Color = vec4(ambient,1) * texture(textureSampler, pass_textureCoords) ;
		}
		else{
			out_Color =  calcBlur(pass_textureCoords,textureSampler,mouseDir);
		}
		if(select == 1){
			out_Color *= vec4(color,1)*2;
		}
	}
	else if(view == 1){
		out_Color = vec4(1-texture(textureSampler, pass_textureCoords).xyz,1);
		if(select == 1){
			out_Color *= vec4(1-color,1)*2;
		}
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
	if(select==1 || view ==3){
		float limit = 0.002*distance;
		if(pass_textureCoords.x <= limit || pass_textureCoords.y <= limit || pass_textureCoords.x >= 1-limit || pass_textureCoords.y >= 1-limit)
			out_Color = vec4(0,0,0,1);
	}
}