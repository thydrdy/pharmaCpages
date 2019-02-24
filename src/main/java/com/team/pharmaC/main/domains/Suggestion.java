package com.team.pharmaC.main.domains;

import lombok.Data;

@Data
public class Suggestion {
	public Suggestion() {}
	public Suggestion(String Answer) { this.Answer=Answer;}
	private String query;
	private String Answer;
}
