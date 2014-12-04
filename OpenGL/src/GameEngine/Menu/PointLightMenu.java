package GameEngine.Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GameEngine.PointLight;
import GameEngine.Vector3f;

public class PointLightMenu extends Component {
	public JLabel position = new JLabel();
	public JTextField posX = new JTextField();
	public JTextField posY = new JTextField();
	public JTextField posZ = new JTextField();
	
	public JLabel color = new JLabel();
	public JTextField colR = new JTextField();
	public JTextField colG = new JTextField();
	public JTextField colB = new JTextField();
	
	JPanel panel = new JPanel();
	JPanel pos = new JPanel();
	JPanel col = new JPanel();
	
	PointLight light=null;
	
	
	public PointLightMenu(PointLight light){
		this.light = light;
		
		position.setText("XYZ pos:");
		posX.setColumns(5);
		posY.setColumns(5);
		posZ.setColumns(5);
		
		color.setText("RGB Color: ");
		colR.setText(String.valueOf(String.format("%.0f", light.getBaseLight().getColor().GetX())));
		colG.setText(String.valueOf(String.format("%.0f", light.getBaseLight().getColor().GetY())));
		colB.setText(String.valueOf(String.format("%.0f", light.getBaseLight().getColor().GetZ())));
		
		colR.setColumns(5);
		colG.setColumns(5);
		colB.setColumns(5);
		
		
		col.add(color);
		col.add(colR);
		col.add(colG);
		col.add(colB);
		//col.setBackground(new Color(1/255*Integer.valueOf(colR.getText()),1/255*Integer.valueOf(colG.getText()),1/255*Integer.valueOf(colB.getText())));
		col.setBackground(new Color(0,255,0));
		
		posX.setText(String.valueOf(String.format("%.2f", light.getPosition().GetX())));
		posY.setText(String.valueOf(String.format("%.2f", light.getPosition().GetY())));
		posZ.setText(String.valueOf(String.format("%.2f", light.getPosition().GetZ())));
		
		
		pos.add(position);
		pos.add(posX);
		pos.add(posY);
		pos.add(posZ);
		pos.setBackground(new Color(255,0,0));
		

		panel.setLayout(new BoxLayout(panel, 1));
		panel.add(pos);
		panel.add(col);
	}
	
	public void getData(){
		if(light==null){
			return;
		}
		getData(light.getPosition().GetX(),light.getPosition().GetY(),light.getPosition().GetZ());
	}
	
	public void getData(float x, float y, float z){
		if(!posX.isFocusOwner())
			posX.setText(String.valueOf(String.format("%.2f", x)));
		if(!posY.isFocusOwner())
			posY.setText(String.valueOf(String.format("%.2f", y)));
		if(!posZ.isFocusOwner())
			posZ.setText(String.valueOf(String.format("%.2f", z)));
	}
	
	public void setData(){
//		float X = Float.parseFloat(posX.getText().replace(',', '.'));
//		float Y = Float.parseFloat(posY.getText().replace(',', '.'));
//		float Z = Float.parseFloat(posZ.getText().replace(',', '.'));
//		System.out.println("string je: "+X+" "+Y+" "+Z);
//		Vector3f newPosition = new Vector3f(X,
//											Y,
//											Z);
//		light.setPosition(newPosition);
		
		Vector3f newColor = new Vector3f(Float.parseFloat(colR.getText()),
										 Float.parseFloat(colG.getText()),
										 Float.parseFloat(colB.getText()));
		light.getBaseLight().setColor(newColor);
		
	}
	
	public void setLight(){
	}
	
	public JPanel getPanel(){
		return panel;
	}
}
