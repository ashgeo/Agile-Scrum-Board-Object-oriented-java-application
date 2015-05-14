package scrum.Application;

import java.util.List;

public interface DaoLayer {
	public Story createStory(Story story);

	public List<Story> listStories();

	public Story deleteStory(Story story);

	public Story compelteStory(Story story);

	public Task addTaskToStory(Task task);

	public List<Task> listTasks(String id);

	public Task deleteTask(Task task);

	public List<Task> updateTask(Task task);

	public List<Task> moveTask(Task task);
}
