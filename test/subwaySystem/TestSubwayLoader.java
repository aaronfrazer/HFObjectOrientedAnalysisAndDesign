package subwaySystem;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSubwayLoader
{
	private static String fileDir = "res/files/";

	@Test
	public void testLoadStations() throws IOException
	{
		SubwayLoader loader = new SubwayLoader();
		Subway objectville = loader.loadFromFile(new File(fileDir + "ObjectvilleSubway.txt"));

		assertTrue(objectville.hasStation("DRY Drive"));
		assertTrue(objectville.hasStation("Weather-O-Rama, Inc."));
		assertTrue(objectville.hasStation("Boards 'R' Us"));
	}

	@Test
	public void testLoadConnections() throws IOException
	{
		SubwayLoader loader = new SubwayLoader();
		Subway objectville = loader.loadFromFile(new File(fileDir + "ObjectvilleSubway.txt"));

		assertTrue(objectville.hasConnection("DRY Drive", "Head First Theater", "Meyer Line"));
		assertTrue(objectville.hasConnection("LSP Lane", "JavaBeans Boulevard", "Booch Line"));
		assertTrue(objectville.hasConnection("OOA&D Oval", "Head First Labs", "Gamma Line"));

	}
}