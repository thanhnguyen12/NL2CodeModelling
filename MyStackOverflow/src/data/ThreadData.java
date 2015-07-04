/**
 * 
 */
package data;

import java.io.Serializable;
import java.util.ArrayList;

import data.QuestionData;

/**
 * @author anhnt_000
 *
 */
public class ThreadData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8224766529163408966L;
	
	private QuestionData question;
	
	public ArrayList<AnswerData> answers;

	public ThreadData(QuestionData question, ArrayList<AnswerData> answers) {
		this.question = question;
		this.answers = answers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ThreadData [question=");
		builder.append(question);
		builder.append(", answers=");
		builder.append(answers);
		builder.append("]");
		return builder.toString();
	}

	public QuestionData getQuestion() {
		return question;
	}

	public void setQuestion(QuestionData question) {
		this.question = question;
	}	

	
	
}
