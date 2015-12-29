#version 130

struct BaseLight{
	vec3 color;
	float intensity;
};

struct DirectionalLight{
	vec3 direction;
	BaseLight baseLight;
};

struct PointLight{
	vec3 position;
	BaseLight baseLight;
	vec3 attenuation;
	float range;
};

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 worldPos;
in vec3 toCamera;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform sampler2D specularSampler;
uniform sampler2D normalSampler;

uniform DirectionalLight sun;
uniform PointLight pointLight;

uniform vec3 eyePos;
uniform vec3 ambient;

uniform int receiveLight;

uniform float specularPower;
uniform float specularIntensity;

uniform int useLights;
uniform int useAmbient;
uniform int useTexture;
uniform int useSpecular;
uniform int useNormalMap;
uniform int useSpecularMap;

vec4 calcLight(BaseLight baseLight, vec3 direction, vec3 normal){
	vec3 unitNormal = normalize(normal);
	vec3 initVectorToCamera = normalize(toCamera);
	direction = normalize(direction);
	
    float nDotl = dot(unitNormal, direction);
    float bringhtness = max(nDotl, 0.0);
    vec3 difuse = bringhtness * baseLight.color;
    
	vec3 finalSpecular = vec3(0);
	if(useSpecular > 0.5){
	    vec3 lightDirection = -direction;
	    vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);
	    
	    float specularFactor = dot(reflectedLightDirection, initVectorToCamera);
	    specularFactor = max(specularFactor, 0.0);
	    float dampedFactor = pow(specularFactor, specularPower);
	    finalSpecular = dampedFactor * baseLight.color;
    };
	return vec4(max(difuse, 0.2) * baseLight.intensity + finalSpecular, 1);
}

vec4 calcPointLight(PointLight pointLight, vec3 normal){
    vec3 lightDirection = pointLight.position - worldPos;
    float distanceToPoint = length(lightDirection);
    
    if(distanceToPoint > pointLight.range){
        return vec4(0,0,0,0);
    }
    
    lightDirection = normalize(lightDirection);
    
    vec4 color = calcLight(pointLight.baseLight, lightDirection, normal);
    
    float attenuation = pointLight.attenuation.x + 
                        pointLight.attenuation.y * distanceToPoint + 
                        pointLight.attenuation.z * distanceToPoint * distanceToPoint +
                        0.0001;
                  
    return color / attenuation;   
}

vec4 calcDirectionalLight(DirectionalLight light, vec3 normal){
	return calcLight(light.baseLight, light.direction , normal);
}

vec4 calcDirectionalLightSpecular(DirectionalLight sun, vec3 normal){
	float diffuseFactor = dot(normal, -sun.direction);
	vec4 diffuseColor = vec4(sun.baseLight.color, 1.0) * 1 * diffuseFactor;
        
    vec3 directionToEye = normalize(eyePos - worldPos);
    vec3 reflectDirection = normalize(reflect(sun.direction, normal));
    
    float specularFactor = dot(directionToEye, reflectDirection);
    specularFactor = pow(specularFactor,specularPower);
    
    vec4 specularColor = vec4(0);
    
    if(specularFactor > 0)
        specularColor = vec4(sun.baseLight.color, 1.0) * specularIntensity * specularFactor;
    
    if(useSpecularMap > 0.5)
    	specularColor *= texture(specularSampler, pass_textureCoords).x;
    	
    return specularColor * sun.baseLight.intensity;
}

void main(){
	out_Color = vec4(1);
	vec3 normal = surfaceNormal;
	
	if(useNormalMap > 0.5)
		normal += (texture(normalSampler, pass_textureCoords).rgb * 2.0 - 1.0) / 100;
	
	if(useAmbient > 0.5)
		out_Color = vec4(ambient, 1);
	
	if(useLights > 0.5 && receiveLight > 0.5){
		vec4 totalLight = vec4(0); 
		totalLight += calcDirectionalLight(sun, normal);
		totalLight += calcPointLight(pointLight, normal);
		
		out_Color *= max(totalLight, 0.2);
	}
	
	if(useTexture > 0.5)
		out_Color *= texture(textureSampler, pass_textureCoords);
	else
		out_Color *= vec4(vec3(0.5), 1);
	
		
}