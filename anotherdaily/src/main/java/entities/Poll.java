package entities;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
public class Poll {
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	LocalDate first_asked;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	LocalDate last_asked;
	long poll_id;
	
	ZoneId standardZone = ZoneId.of("Europe/Paris"); //Save standardZone somewere?
	//java does not like array of objects so lets do linkedlist
	LinkedList<Question> questions;
	
	//make empty poll
	public Poll(LocalDate first, LocalDate last, long poll_id) {
		//test with regex 
	
		setFirst_asked(first);
		setLast_asked(last);
		this.poll_id = poll_id;
		
		questions= new LinkedList<>();
	}
	

	

	
	//I will use LocalDate as we dont need to be perfectly precise
	
	public long getPoll_id() {
		return poll_id;
	}



	public void setPoll_id(long poll_id) {
		this.poll_id = poll_id;
	}



	public LocalDate getFirst_asked() {
		return first_asked;
	}



	public void setFirst_asked(LocalDate first_asked) {
		isPast(first_asked); //Date gotta be in past
		this.first_asked = first_asked;
	
		
	}



	public LocalDate getLast_asked() {
		return last_asked;
	}



	public void setLast_asked(LocalDate last_asked) {
		isPast(last_asked); //Date gotta be in past
		this.last_asked = last_asked;
	}



	private void isPast(LocalDate date) {
		if(!date.isAfter(LocalDate.now(standardZone))) {
			return;
		}
		else {
			throw new IllegalArgumentException("You cannot set date as future day(compared to Paris Timezone)");
		}
	}
	
	
	public void addQuestion(Question q) {
		questions.add(q);
	}
	
	public LinkedList<Question> getQuestions() {
		return questions;
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LinkedList<String> hello = new LinkedList<>();
		
		
	}

}
