/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrum.Application;

import java.util.ArrayList;
import java.util.List;

public class Story {

	private String id;
	private String description;
	private String status;
	DaoLayer daoLayer = new daoLayerImpl();

	private List<Task> tasks;

	public List<Task> getTasks() {

		if (tasks == null) {
			tasks = new ArrayList<Task>();
		}
		return tasks;
	}

	public Story(String id, String description) {

		this.id = id;
		this.description = description;
		this.status = "To Do";
	}

	public Story() {
	}

	public String getId() {
		return id;
	}

	public void SetId(String id) {
		this.id = id;
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

}
