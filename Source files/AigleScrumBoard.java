package scrum.Application;

import java.util.ArrayList;
import java.util.List;

public class AigleScrumBoard {

	private List<Story> stories;

	DaoLayer daoLayer = new daoLayerImpl();

	public AigleScrumBoard() {

	}

	public List<Story> getStories() {
		stories = daoLayer.listStories();

		if (stories == null) {

			stories = new ArrayList<Story>();
		}

		return stories;
	}

}
