varying vec3 color;

void main(){
	color = vec3(0.5,0,0.5);
	gl_Position = ftransform();
}