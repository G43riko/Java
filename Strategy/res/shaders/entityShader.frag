#version 400 core

const int MAX_LIGHTS = 4;

in float distance;
in float visibility;
in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector[MAX_LIGHTS];
in vec3 toCameraVector;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform sampler2D normalSampler;

uniform int view;

uniform int select;
uniform int blur;
uniform int texture;
uniform int light;
uniform int specular;
uniform int fog;

uniform vec3 color;
uniform vec3 ambient;
uniform vec3 lightColor[MAX_LIGHTS];
uniform vec3 attenuation[MAX_LIGHTS];
uniform float range[MAX_LIGHTS];
uniform vec2 mouseDir;
uniform float shineDumper;
uniform float reflectivity;


void main(){
	if(view == 0){
		if(fog == 0 || visibility > 0.0001){
			if(blur == 0){
				vec3 totalDiffuse = vec3(0.0);
				vec3 totalSpecular = vec3(0.0);
				vec3 unitNormal = normalize(surfaceNormal);
				vec3 unitVectorToCamera = normalize(toCameraVector);
				for(int i=0 ; i<MAX_LIGHTS ; i++){
					if(lightColor[i] == vec3(0,0,0)){
						continue;
					}
					if(light != 1 ){
						break;
					}
					float distToLight = length(toLightVector[i]);
					
					float attFactor = attenuation[i].x + attenuation[i].y * distToLight + attenuation[i].y * distToLight * distToLight; 
					vec3 unitLightVector = normalize(toLightVector[i]);
					
					if(specular==1){
						vec3 lightDirection = -unitLightVector;
						vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);
						float specularFactor = dot(reflectedLightDirection, unitVectorToCamera);
						specularFactor = max(0.0, specularFactor);
						float damperFactor = pow(specularFactor, shineDumper);
						totalSpecular = totalSpecular +  damperFactor * reflectivity * lightColor[i];
					}
					float nDotl = dot(unitNormal,unitLightVector);
					float brightness = max(nDotl,0.0);
					totalDiffuse = totalDiffuse + (brightness * lightColor[i])/attFactor ;
				}
				totalDiffuse = max(totalDiffuse,0.2);
				out_Color =  vec4(ambient,1) * vec4(totalDiffuse,1.0);
				
				if(texture==1){
					out_Color *= texture(textureSampler, pass_textureCoords);
					
				}
				out_Color += vec4(totalSpecular, 1.0);
				if(fog == 1){
					out_Color = mix(vec4(ambient,1.0), out_Color, visibility);
				}
			}
			if(select == 1){
				out_Color /= 2;
			}
		}
		else{
			out_Color = vec4(ambient, 1.0);
		}
		
		//out_Color = floor(out_Color*10)/10;

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
		if(color!=vec3(0,0,0)){
			out_Color = vec4(color,1);
		}
		else
			out_Color = vec4(0.5, 0.5, 0.5, 1);
	}
	if(select==1){
		float limit = 0.002*distance*2;
		if(pass_textureCoords.x <= limit || pass_textureCoords.y <= limit || pass_textureCoords.x >= 1-limit || pass_textureCoords.y >= 1-limit)
			out_Color = vec4(0,0,0,1);
	}
	else if(view == 4){
		out_Color = vec4(abs(surfaceNormal),1);
	}
	
}