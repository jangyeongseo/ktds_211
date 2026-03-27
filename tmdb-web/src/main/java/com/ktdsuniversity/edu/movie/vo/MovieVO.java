package com.ktdsuniversity.edu.movie.vo;

public class MovieVO {
	private String movieId;
	private String posterUrl;
	private String title;
	private String movieRating;
	private String openDate;
	private String openCountry;
	private int runningTime;
	private String introduce;
	private String synopsis;
	private String originalTitle;
	private String state;
	private String language;
	private long budget;
	private long profit;

	public MovieVO() {
	}

	public MovieVO(String movieId, String posterUrl, String title, String movieRating, String openDate,
			String openCountry, int runningTime, String introduce, String synopsis, String originalTitle, String state,
			String language, long budget, long profit) {
		super();
		this.movieId = movieId;
		this.posterUrl = posterUrl;
		this.title = title;
		this.movieRating = movieRating;
		this.openDate = openDate;
		this.openCountry = openCountry;
		this.runningTime = runningTime;
		this.introduce = introduce;
		this.synopsis = synopsis;
		this.originalTitle = originalTitle;
		this.state = state;
		this.language = language;
		this.budget = budget;
		this.profit = profit;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getOpenCountry() {
		return openCountry;
	}

	public void setOpenCountry(String openCountry) {
		this.openCountry = openCountry;
	}

	public int getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public long getProfit() {
		return profit;
	}

	public void setProfit(long profit) {
		this.profit = profit;
	}

}