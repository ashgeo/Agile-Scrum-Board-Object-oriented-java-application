package scrum.Application;

import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

public class Main {

	private static int optint;
	private static Scanner keyin;

	public static void main(String args[]) {
		MainList();

	}

	public static String readStoryID() {

		System.out.println("Enter the Story ID \n");
		String id = readInput();
		return id;
	}

	public static String readStoryDescription() {
		System.out.println("Enter the Story  Description \n");
		String description = readInput();
		return description;
	}

	public static String readStoryStatus() {
		System.out.println("Enter the Column Name to Move");
		System.out.println("Enter IP: In Process");
		System.out.println("ENTER TV: To Verify");
		System.out.println("Enter C:Completed");
		String storystatus = readInput();
		return storystatus;
	}

	public static String readTaskID() {

		System.out.println("Enter the Task ID \n");
		String id = readInput();
		return id;
	}

	public static String readTaskDescription() {
		System.out.println("Enter the Task  Description \n");
		String description = readInput();
		return description;
	}

	public static String readTaskStatus() {
		System.out.println("Enter the Column Name to Move");
		System.out.println("Enter IP: In Process");
		System.out.println("ENTER TV: To Verify");
		System.out.println("Enter C:Completed");
		String storystatus = readInput();
		return storystatus;

	}

	private static String readInput() {
		keyin = new Scanner(System.in);
		String value = keyin.nextLine();
		return value;
	}

	public static int nextInt() {

		keyin = new Scanner(System.in);
		optint = keyin.nextInt();
		return optint;
	}

	public static void MainList() {

		Manager manger = new Manager();
		int count = 0;

		System.out.println("[1]Create New Story");
		System.out.println("[2]List Stories");
		System.out.println("[3]Delete  story");
		System.out.println("[4]Complete  story");
		System.out.println("[5]Add Task ");
		System.out.println("[6]List Task");
		System.out.println("[7]Delete Task");
		System.out.println("[8]Update Task");
		System.out.println("[9]Move Task");

		try {
			System.out.println("Insert selection :\n");
			count = nextInt();
			if (count > 9) {
				System.out.println("Invalid Selection");
				MainList();
			}
			switch (count) {

			case 1:
				String id = readStoryID();
				String description = readStoryDescription();
				manger.createAndAddStory(id, description);

				MainList();
				break;
			case 2:
				manger.listStoriesAndprint();
				MainList();
				break;
			case 3:
				manger.listStoriesAndprint();
				id = readStoryID();
				manger.deleteStory(id);
				System.out.println("Story deleted successfully\n");
				MainList();
				break;
			case 4:
				manger.listStoriesAndprint();
				id = readStoryID();
				manger.compelteStory(id);
				System.out.println("Story Completed");
				MainList();
				break;
			case 5:
				manger.listStoriesAndprint();
				id = readStoryID();
				manger.listTasks(id);
				String tid = readTaskID();
				String tdescription = readTaskDescription();
				manger.addTaskToStory(id, tid, tdescription);
				System.out.println("Task  Added successfully\n");
				MainList();
				break;

			case 6:
				manger.listStoriesAndprint();
				id = readStoryID();
				manger.listTasks(id);
				MainList();
				break;
			case 7:
				manger.listStoriesAndprint();
				id = readStoryID();
				manger.listTasks(id);
				tid = readTaskID();

				manger.deleteTask(id, tid);
				MainList();
				break;
			case 8:
				manger.listStoriesAndprint();
				id = readStoryID();
				manger.listTasks(id);
				tid = readTaskID();
				tdescription = readTaskDescription();
				manger.updateTask(id, tid, tdescription);
				MainList();
				break;
			case 9:
				manger.listStoriesAndprint();
				id = readStoryID();
				manger.listTasks(id);
				tid = readTaskID();
				String status = readTaskStatus();
				manger.moveTask(id, tid, status);
				MainList();
				break;
			default:
				System.out.println("selection invalid");
				break;
			}
		} catch (Exception ex) {
			System.out.println("Invalid input format");
		}
	}

}
