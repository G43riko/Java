#version 130

in vec2 textureCoords;

out vec4 out_Color;

uniform sampler2D guiTexture;
uniform vec2 mouseMove;

uniform int useCameraBlur;
uniform int useAntiAliasing;
uniform int typeOfView;

vec4 calcBlur(vec2 textureCoords, sampler2D texture, vec2 direction){
	float blurSize = 1.0/256.0;
	vec4 sum = vec4(0.0);
	float hstep = direction.x;
    float vstep = direction.y;
    
	sum += texture(texture, vec2(textureCoords.x - 4 * blurSize * hstep, textureCoords.y - 4.0 * blurSize * vstep)) * 0.05;
	sum += texture(texture, vec2(textureCoords.x - 3 * blurSize * hstep, textureCoords.y - 3.0 * blurSize * vstep)) * 0.09;
	sum += texture(texture, vec2(textureCoords.x - 2 * blurSize * hstep, textureCoords.y - 2.0 * blurSize * vstep)) * 0.12;
	sum += texture(texture, vec2(textureCoords.x - 1 * blurSize * hstep, textureCoords.y - 1.0 * blurSize * vstep)) * 0.15;
	//sum += texture(texture, vec2(textureCoords.x , textureCoords.y)) * 0.16;
	sum += texture(texture, vec2(textureCoords.x + 4 * blurSize * hstep, textureCoords.y + 4.0 * blurSize * vstep)) * 0.05;
	sum += texture(texture, vec2(textureCoords.x + 3 * blurSize * hstep, textureCoords.y + 3.0 * blurSize * vstep)) * 0.09;
	sum += texture(texture, vec2(textureCoords.x + 2 * blurSize * hstep, textureCoords.y + 2.0 * blurSize * vstep)) * 0.12;
	sum += texture(texture, vec2(textureCoords.x + 1 * blurSize * hstep, textureCoords.y + 1.0 * blurSize * vstep)) * 0.15;
	
	return sum;
}

void main(void){
	out_Color = texture(guiTexture,textureCoords);
	
	//antialiasing
	if(useAntiAliasing > 0.5){
		
	}
	
	if(useCameraBlur > 0.5){
		out_Color *= 0.16;
		out_Color += calcBlur(textureCoords, guiTexture, mouseMove);
	}
	
	//greyScale
	if(typeOfView == 1){
		float average =  (out_Color.x + out_Color.y + out_Color.z ) / 3;
		out_Color = vec4(average, average, average, 1);
	}
	
	//invert
	if(typeOfView == 2){
		out_Color = vec4(1 - out_Color.xyz, 1);
	}
	
	//draw
	if(0 > 0.5){
		float value = 8; 
		out_Color = round(out_Color * value) / value;;
	}
	
	//min and max
	if(0 > 0.5){
		vec3 min = vec3(0,0,0);
		vec3 max = vec3(1,1,1);
		
		float r = mix(min.x, max.x, out_Color.x);
		float g = mix(min.y, max.y, out_Color.y);
		float b = mix(min.z, max.z, out_Color.z);
		out_Color = vec4(r, g, b, 1);
	}
	
	//ambient occlusion
	if(0 > 0.5){
		float offset = 0.001;
		vec3 normal = out_Color.xyz;
		vec3 invert = 1 - texture(guiTexture,textureCoords+vec2(offset, offset)).xyz;
		vec3 result = 1 - (normal + invert);
		float edge = 0.1;
		if(result.x > edge || result.y > edge || result.z > edge)
			out_Color -= vec4(1, 1, 1, 0);
	}
	
	
}