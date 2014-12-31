package GUI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WindowsListener implements WindowListener {
	private JFrame frame;
	
	public WindowsListener(JFrame frame){
		this.frame=frame;
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		if(JOptionPane.showConfirmDialog(this.frame, "Naozaj chceö skonËiù?","posledn· öanca na n·vrat",0)==JOptionPane.YES_OPTION){
			frame.dispose();
			System.exit(1);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}

}
