#version 130

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 worldPos;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform sampler2D specularSampler;

uniform vec3 sunDirection;
uniform vec3 sunColor;
uniform vec3 eyePos;
uniform vec3 ambient;

uniform float specularPower;
uniform float specularIntensity;

uniform int useLights;
uniform int useAmbient;
uniform int useTexture;
uniform int useSpecular;
uniform int useSpecularMap;

void main(){
	vec3 sunDiffuse = vec3(1);
	if(useLights > 0.5){
		float nDotl = dot(normalize(surfaceNormal), normalize(sunDirection));
		sunDiffuse = max(max(nDotl,0.0) * sunColor, 0.2);
	}
	
	vec4 actAmbient = vec4(1);
	if(useAmbient > 0.5){
		actAmbient = vec4(ambient, 1);
	}
	out_Color = actAmbient * vec4(sunDiffuse, 1.0);
	
	if(useTexture > 0.5){
		out_Color *= texture(textureSampler, pass_textureCoords);
		//out_Color *= texture(specularSampler, pass_textureCoords);
	}
	else{
		out_Color *= vec4(vec3(0.5), 1);
	}
	
	if(useSpecular > 0.5){
		float diffuseFactor = dot(surfaceNormal, -sunDirection);
		vec4 diffuseColor = vec4(sunColor,1.0) * 1 * diffuseFactor;
	        
	    vec3 directionToEye = normalize(eyePos - worldPos);
	    vec3 reflectDirection = normalize(reflect(sunDirection, surfaceNormal));
	    
	    float specularFactor = dot(directionToEye, reflectDirection);
	    specularFactor = pow(specularFactor,specularPower);
	    
	    vec4 specularColor = vec4(0);
	    if(specularFactor >0){
	        specularColor = vec4(sunColor,1.0) * specularIntensity * specularFactor;    
	    }
	    
	    if(useSpecularMap > 0.5){
	    	specularColor *= texture(specularSampler, pass_textureCoords).x;
	    }
	    out_Color += specularColor;
    }
}