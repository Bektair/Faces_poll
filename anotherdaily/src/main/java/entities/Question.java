package entities;

import java.time.LocalDate;
import java.util.LinkedList;

public class Question {
	private String questionTxt;
	private int q_id;
	//parallel arrays
	private LinkedList<Alternative> alternatives;
	
	public Question(String txt, int q_id) {
		questionTxt = txt;
		alternatives = new LinkedList<>();
		this.q_id = q_id;
	}
	
	public void addAlternative(Alternative a) {
		alternatives.add(a);
	}

	public int getQ_id() {
		return q_id;
	}

	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}

	public String getQuestionTxt() {
		return questionTxt;
	}

	public void setQuestionTxt(String questionTxt) {
		this.questionTxt = questionTxt;
	}

	public LinkedList<Alternative> getAlternatives() {
		return alternatives;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
