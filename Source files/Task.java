/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrum.Application;

/**
 *
 * @author ash
 */
public class Task {

	private String id;
	private String description;
	private String storyid;
	private String status;

	public Task(String id, String description, String storyid) {

		this.id = id;
		this.description = description;
		this.storyid = storyid;
		this.status = "ToDo";
	}

	public Task() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String Id) {
		this.id = Id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStoryId() {
		return storyid;
	}

	public void setStoryId(String storyId) {
		this.storyid = storyId;
	}

}
