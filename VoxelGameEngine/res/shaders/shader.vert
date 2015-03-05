#version 130

in vec3 position;
in vec2 texture;
in vec3 normal;

out vec2 textureCoords;
out vec3 normals;

void main(){
	gl_Position = vec4(position,1);
	textureCoords = texture;
	normals = normal;
}