#version 330

const int MAX_POINTS_LIGHTS = 4;

in vec2 texCoord0;
in vec3 normal0;
in vec3 worldPos0;

out vec4 fragColor; 


struct BaseLight{
    vec3 color;
    float intensity;
};

struct DirectionalLight{
    BaseLight base;
    vec3 direction;
};

struct Attenuation{
    float constant;
    float linear;
    float exponent;
};

struct PointLight{
    BaseLight baseLight;
    Attenuation atten;
    vec3 position;
};

uniform vec3 baseColor;
uniform vec3 eyePos;
uniform vec3 ambientLight;
uniform sampler2D sampler;

uniform float specularIntensity;
uniform float specularPower;

uniform DirectionalLight directionalLight;
uniform PointLight pointLights[MAX_POINTS_LIGHTS];

vec4 calcLight(BaseLight baseLight, vec3 direction, vec3 normal){
    float diffuseFactor = dot(normal, -direction);

    vec4 diffuseColor = vec4(0,0,0,0);
    vec4 specularColor = vec4(0,0,0,0);
        
    if(diffuseFactor > 0){
        diffuseColor = vec4(baseLight.color,1.0) * baseLight.intensity * diffuseFactor;
        
        vec3 directionToEye = normalize(eyePos - worldPos0);
        vec3 reflectDirection = normalize(reflect(direction, normal));
        
        float specularFactor = dot(directionToEye, reflectDirection);
        specularFactor = pow(specularFactor,specularPower);
        
        if(specularFactor >0){
            specularColor = vec4(baseLight.color,1.0) * specularIntensity * specularFactor;    
        }
    }
    
    return diffuseColor + specularColor;
};

vec4 calcDirectionalLight(DirectionalLight directionalLight, vec3 normal){
    return calcLight(directionalLight.base,-directionalLight.direction, normal);
};

vec4 calcPointLight(PointLight pointLight, vec3 normal){
    vec3 lightDirection = worldPos0 - pointLight.position;
    float distanceToPoint = length(lightDirection);
    lightDirection = normalize(lightDirection);
    
    vec4 color = calcLight(pointLight.baseLight, lightDirection, normal);
    
    float attenuation = pointLight.atten.constant + 
                        pointLight.atten.linear * distanceToPoint + 
                        pointLight.atten.exponent * distanceToPoint * distanceToPoint +
                        0.0001;
                        
    return color / attenuation;   
};

void main()
{

    vec4 totalLight = vec4(ambientLight,1);
    vec4 color = vec4(baseColor,1);
    vec4 textureColor = texture(sampler, texCoord0.xy);
    
    if(textureColor != vec4(0,0,0,0))
	   color *= textureColor;
       
    vec3 normal = normalize(normal0);
    
    totalLight += calcDirectionalLight(directionalLight, normal);   
                              
    for(int i=0 ; i<MAX_POINTS_LIGHTS ; i++){     
        if(pointLights[i].baseLight.intensity > 0){
            totalLight += calcPointLight(pointLights[i], normal);
        }
    }                              
                              
    fragColor = color * totalLight;
}                                 