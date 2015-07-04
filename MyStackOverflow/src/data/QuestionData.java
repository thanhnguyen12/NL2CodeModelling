package data;

import java.io.Serializable;

/**
 * @author thanhnguyen
 * Created on June, 21
 */
public class QuestionData implements Serializable {

	/**
	 * Automatically generated serialVersionUID 
	 */
	private static final long serialVersionUID = 6583277698450669789L;
	
	/** questionId is the same as the post's threadId */
	private long id;
	
	/** question title */
	private String title;
	
	private String content;
	
	private long score;
	
	/** the person posting this question */
	private PosterData poster;
	
	/**
	 * Constructor
	 * @return
	 */
	public QuestionData(long id, String title, String content, long score, PosterData poster) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.score = score;
		this.poster = poster;
	}

	public long getQuestionId() {
		return id;
	}

	public void setQuestionId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getQuestionScore() {
		return score;
	}

	public void setQuestionScore(long score) {
		this.score = score;
	}

	public PosterData getPoster() {
		return poster;
	}

	public void setPoster(PosterData poster) {
		this.poster = poster;
	}
	
}
