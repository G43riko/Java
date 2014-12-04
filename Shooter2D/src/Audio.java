import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;



public class Audio {
	private static Clip clip;
	private static FloatControl gainControl;
	private static AudioInputStream audioInputStream;
	
	
	public static boolean Play(String co){
		switch(co){
			case "laser":
				try {
			        Audio.audioInputStream= AudioSystem.getAudioInputStream(ClassLoader.getSystemResourceAsStream("\\Sounds\\laser1.wav"));
			        Audio.clip = AudioSystem.getClip();
			        Audio.clip.open(Audio.audioInputStream);
			        
			        Audio.gainControl=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			        Audio.gainControl.setValue(-9.0f);
			    } catch(Exception ex) {
			        System.out.println(ex);
			        return false;
			    }
				break;
			case "dead":
				try {
			        Audio.audioInputStream= AudioSystem.getAudioInputStream(ClassLoader.getSystemResourceAsStream("\\Sounds\\dead.wav"));
			        Audio.clip = AudioSystem.getClip();
			        Audio.clip.open(Audio.audioInputStream);
			        
			        Audio.gainControl=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			        Audio.gainControl.setValue(-9.0f);
			    } catch(Exception ex) {
			        System.out.println(ex);
			        return false;
			    }
				break;
			case "demage":
				try {
					int i=(int)Math.floor(Math.random()*3)+1;
			        Audio.audioInputStream= AudioSystem.getAudioInputStream(ClassLoader.getSystemResourceAsStream("\\Sounds\\demage"+i+".wav"));
			        Audio.clip = AudioSystem.getClip();
			        Audio.clip.open(Audio.audioInputStream);
			        
			        Audio.gainControl=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			        Audio.gainControl.setValue(-9.0f);
			    } catch(Exception ex) {
			        System.out.println(ex);
			        return false;
			    }
				break;
			
		}
		
		Audio.clip.start();
		
		return true;
	}
}
