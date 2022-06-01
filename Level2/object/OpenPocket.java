package Level2.object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OpenPocket extends ParentObject 
{
	public OpenPocket()
	{
		name = "Pocket1";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Level2/objectsImport/closedPocket.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
