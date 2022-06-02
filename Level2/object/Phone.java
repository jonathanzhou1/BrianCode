package Level2.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Phone extends ParentObject 
{
	public Phone()
	{
		name = "Phone";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Level2/objectsImport/phone.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
