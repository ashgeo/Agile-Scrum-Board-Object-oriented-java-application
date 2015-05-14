package scrum.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class daoLayerImpl implements DaoLayer {
	private Connection conn;
	private ResultSet rs;

	private void getConnection() {
		try {

			String Db = "test";// Database name
			String connectionHost = "jdbc:mysql://127.0.0.1/" + Db;// Host name
																	// with db
																	// name
			String userName = "root";// database username
			String Password = "";// database password
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connectionHost, userName,
					Password);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public Story createStory(Story story) {

		try {
			getConnection();
			final String selectquery = "SELECT count(*) from story WHERE id = ?";
			final PreparedStatement psselect = conn
					.prepareStatement(selectquery);
			psselect.setString(1, story.getId());
			final ResultSet resultSet = psselect.executeQuery();
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);
				if (count > 0) {
					System.out.println("Story ID " + story.getId()
							+ "is already exist try another Id ");

				} else {
					PreparedStatement ps = conn
							.prepareStatement("INSERT INTO STORY VALUES( ?, ?,? )");
					ps.setString(1, story.getId());
					ps.setString(2, story.getDescription());
					ps.setString(3, story.getStatus());
					ps.executeUpdate();

				}

			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return story;

	}

	public List<Story> listStories() {
		List<Story> stories = null;
		try {

			getConnection();
			String selectquery = "SELECT * FROM story";

			final PreparedStatement psselect = conn
					.prepareStatement(selectquery);
			rs = psselect.executeQuery();

			stories = new ArrayList<Story>();
			while (rs.next()) {
				String id = rs.getString("id");
				String description = rs.getString("description");
				Story story = new Story(id, description);
				stories.add(story);

			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return stories;

	}

	@Override
	public Story compelteStory(Story story) {

		try {

			getConnection();
			final String selectquery = "SELECT count(*) from story WHERE id = ?";

			final PreparedStatement psselect = conn
					.prepareStatement(selectquery);
			psselect.setString(1, story.getId());

			final String selectstatus = "SELECT status from story WHERE id = '"
					+ story.getId() + "'";
			;
			final PreparedStatement psselectstatus = conn
					.prepareStatement(selectstatus);
			rs = psselectstatus.executeQuery();

			final ResultSet resultSet = psselect.executeQuery();
			// String stat = "ToDo";
			while (rs.next()) {
				if ("completed".equalsIgnoreCase(rs.getString(1))) {

					System.out
							.println("Story already completed try Another one");

					return story;
				}
			}
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);

				if (count == 0) {

					System.out
							.println("Story ID "
									+ story.getId()
									+ " is does not exist in the list try another one ");

				} else {

					final String updatetquery = "update story set status='"
							+ story.getStatus() + "' where id='"
							+ story.getId() + "'";
					final String updatetasktquery = "update task set taskstatus='"
							+ story.getStatus()
							+ "' where storyid='"
							+ story.getId() + "'";

					final PreparedStatement pssupdate = conn
							.prepareStatement(updatetquery);
					final PreparedStatement pssupdatetask = conn
							.prepareStatement(updatetasktquery);
					pssupdate.execute();
					pssupdatetask.execute();

				}
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return story;

	}

	@Override
	public Story deleteStory(Story story) {

		try {

			getConnection();
			String deletequery = "SELECT count(*) from story WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(deletequery);
			ps.setString(1, story.getId());

			final ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				final int count = resultSet.getInt(1);

				if (count == 0) {

					System.out.println("Story ID " + story.getId()
							+ "is does not exist in the list try another one ");

				}

				else {
					final String deletestory = "delete from story  where  story.id='"
							+ story.getId() + "'";

					final String deletetask = "delete from task using story,task where  task.storyid='"
							+ story.getId() + "'";
					PreparedStatement pssdelete = conn
							.prepareStatement(deletestory);
					boolean storyresult = pssdelete.execute();
					pssdelete = conn.prepareStatement(deletetask);
					boolean taskresult = pssdelete.execute();
					if (storyresult & taskresult) {
						System.out.println("Task deleted successfully\n");
					}

				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return null;

	}

	@Override
	public Task addTaskToStory(Task task) {

		try {
			getConnection();
			String insertquery = " insert into task (taskid,taskdesc,taskstatus,storyid)"
					+ " values (?,?,?,?) ";
			final PreparedStatement psinsert = conn
					.prepareStatement(insertquery);
			try {
				getConnection();
				psinsert.setString(1, task.getId());
				psinsert.setString(2, task.getDescription());
				psinsert.setString(3, task.getStatus());
				psinsert.setString(4, task.getStoryId());
				psinsert.execute();

			} catch (Exception ex) {

				System.out.println("Task Not added. Pls try again...");

			}

		}

		catch (Exception e) {
			System.out.println(e);

		}

		return null;
	}

	public List<Task> listTasks(String Storyid) {
		List<Task> task = null;
		try {

			getConnection();

			final String selectcount = "SELECT count(*) from task WHERE storyid = ?";
			final PreparedStatement psselectcount = conn
					.prepareStatement(selectcount);
			psselectcount.setString(1, Storyid);
			final ResultSet res = psselectcount.executeQuery();

			task = new ArrayList<Task>();
			while (res.next()) {

				String selectquery = "SELECT * FROM task WHERE storyid= '"
						+ Storyid + "'";

				final PreparedStatement psselect = conn
						.prepareStatement(selectquery);
				rs = psselect.executeQuery();
				while (rs.next()) {
					String id = rs.getString("taskid");
					String description = rs.getString("taskdesc");
					Task tasks = new Task(id, description, Storyid);
					tasks.setStatus(rs.getString("taskstatus"));
					task.add(tasks);
				}
			}

		}

		catch (Exception ex) {
			System.out.println(ex);
		}
		return task;

	}

	public Task deleteTask(Task task) {
		try {
			getConnection();
			final String deletequery = "delete from task where  storyid='"
					+ task.getStoryId() + "' and taskid='" + task.getId() + "'";
			final PreparedStatement pssdelete = conn
					.prepareStatement(deletequery);
			pssdelete.execute();
			System.out.println("Task deleted successfully\n");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public List<Task> updateTask(Task task) {
		try {
			getConnection();
			final String updatetquery = "update task set taskdesc='"
					+ task.getDescription() + "' where storyid='"
					+ task.getStoryId() + "' and taskid='" + task.getId() + "'";

			final PreparedStatement pssupdate = conn
					.prepareStatement(updatetquery);
			pssupdate.execute();

			System.out.println("Status updated \n");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public List<Task> moveTask(Task task) {
		try {
			getConnection();
			
			final String selectcount = "SELECT count(*) from task WHERE storyid = ?";
			final PreparedStatement psselectcount = conn
					.prepareStatement(selectcount);
			psselectcount.setString(1, task.getStoryId());
			final ResultSet res = psselectcount.executeQuery();

			
			while (res.next()) {
				final int count = res.getInt(1);

				if (count == 0) {

					System.out.println("No tasks under the story id " + task.getStoryId()
							+ "");
					
					Main.MainList();
				}
			}
			
			
			final String selectstatus = "SELECT taskstatus from task WHERE taskid = '"
					+ task.getId() + "'";
			;
			final PreparedStatement psselectstatus = conn
					.prepareStatement(selectstatus);
			rs = psselectstatus.executeQuery();

			while (rs.next()) {
				if ("completed".equalsIgnoreCase(rs.getString(1))) {

					System.out
							.println("Task already completed try Another one");

					Main.MainList();
					// return task;
				}
			}

			if ("ToDo".equalsIgnoreCase(task.getStatus())) {
				System.out.println("Cant move to same column try another one");
			} else if ("IP".equalsIgnoreCase(task.getStatus())) {
				task.setStatus("In Process");
			} else if ("TV".equalsIgnoreCase(task.getStatus())) {
				task.setStatus("To Verify");
			} else if ("C".equalsIgnoreCase(task.getStatus())) {
				task.setStatus("Completed");
			} else {
				System.out.println("Invalid selection please try again");
			}

			final String updatetquery = "update task set taskstatus='"
					+ task.getStatus() + "' where storyid='"
					+ task.getStoryId() + "' and taskid='" + task.getId() + "'";

			final PreparedStatement pssupdate = conn
					.prepareStatement(updatetquery);
			pssupdate.execute();

			System.out.println("Status moved to " + task.getStatus());
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	// Test Functions ...............................
	

}