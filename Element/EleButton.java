package Element;

import java.awt.Color;
import javax.swing.JButton;

public class EleButton extends JButton{
		public EleButton(String title,int size,int x,int y,int width,int height) {
			super(title);
			this.setBackground(new Color(76, 168, 255));
			this.setForeground(Color.black);
			this.setFont(Element.getFont(size));
			this.setBounds(x, y, width, height);
		}
}
