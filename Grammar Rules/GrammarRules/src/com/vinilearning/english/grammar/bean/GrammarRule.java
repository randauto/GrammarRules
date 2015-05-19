package com.vinilearning.english.grammar.bean;

public class GrammarRule {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/** tilte of an lesson. */
	private String title = "";

	/** description of lesson. */
	private String description = "";

	/** path to file .html of lesson. */
	private String pathFile = "";

	public GrammarRule() {

	}

	public GrammarRule(String title, String description, String pathFile) {
		this.title = title;
		this.description = description;
		this.pathFile = pathFile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

}
