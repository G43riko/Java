#version 130

in vec3 position;

out vec2 pass_textureCoords;

uniform mat4 transformationMatrix;

void main(){
	gl_Position =   transformationMatrix * vec4(position,1);
	pass_textureCoords = vec2((position.x+1.0)/2.0, 1 - (position.y+1.0)/2.0);
}