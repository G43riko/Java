#version 120

attribute vec3 position;
attribute vec2 texCoords;
            
varying vec4 texColor0;

uniform mat4 transform;

void main()
{
    //color = vec4(clamp(position, 0.0, 1.0),1.0);
	gl_Position = transform * vec4(position, 1.0);
	texColo0 = texCoords;
}