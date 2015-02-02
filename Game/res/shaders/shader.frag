varying vec3 color;

uniform sampler2D texture1;

void main(){
	gl_FragColor = vec4(color, 1) * texture2D(texture1, gl_TexCoord[0].st);
	
}