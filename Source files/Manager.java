/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrum.Application;

import java.util.ArrayList;
import java.util.List;

public class Manager {

	private AigleScrumBoard aigleScrumBoard;
	DaoLayer daoLayer = new daoLayerImpl();

	public AigleScrumBoard getAigleScrumBoard() {

		if (aigleScrumBoard == null) {
			aigleScrumBoard = new AigleScrumBoard();
		}

		return aigleScrumBoard;
	}

	public void createAndAddStory(String id, String description) {
		Story story = new Story(id, description);
		getAigleScrumBoard();
		aigleScrumBoard.getStories().add(story);
		daoLayer.createStory(story);
	}

	public void listStoriesAndprint() {

		List<Story> stories = daoLayer.listStories();
		System.out.println("Current Stories");
		for (Story story : stories) {
			System.out.println(story.getId() + " " + story.getDescription());
		}

	}

	public void compelteStory(String id) {
		Story story = new Story();
		story.SetId(id);
		story.setStatus("Completed");
		daoLayer.compelteStory(story);

	}

	public Story deleteStory(String id) {
		Story story = new Story(id, null);

		if (story.getId().equals(id)) {
			daoLayer.deleteStory(story);
		}
		return story;

	}

	public void addTaskToStory(String storyId, String taskId,
			String taskDescription) {

		Task task = new Task(taskId, taskDescription, storyId);

		getStoryFromAgileScrumBoard(storyId).getTasks().add(task);

		daoLayer.addTaskToStory(task);

	}

	private Story getStoryFromAgileScrumBoard(String storyId) {

		List<Story> stories = getAigleScrumBoard().getStories();

		for (Story story : stories) {

			if (story.getId().equals(storyId)) {

				return story;
			}

		}

		return null;
	}

	public void listTasks(String storyId) {

		getStoryFromAgileScrumBoard(storyId).getTasks();
		List<Task> tasks = daoLayer.listTasks(storyId);

		System.out.println("Current Tasks");
		for (Task task : tasks) {
			System.out.println(task.getId() + " " + task.getDescription() + " "
					+ task.getStatus());
		}

	}

	public void deleteTask(String storyId, String taskId) {

		Task task = new Task();
		task.setId(taskId);
		task.setStoryId(storyId);
		daoLayer.deleteTask(task);

	}

	public void updateTask(String storyId, String taskId, String taskDescription) {
		Task task = new Task(taskId, taskDescription, storyId);

		daoLayer.updateTask(task);

	}

	public void moveTask(String storyId, String taskId, String status) {
		Task task = new Task();
		task.setId(taskId);
		task.setStoryId(storyId);
		task.setStatus(status);
		daoLayer.moveTask(task);

	}
}
