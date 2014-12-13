package tests.vbo;

public class StaticShader extends ShaderProgram{

	private static final String VERTEX_FILE = "vertexShader.txt";
	private static final String FRAGMENT_FILE = "fragmentShader.txt";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		
	}

}
