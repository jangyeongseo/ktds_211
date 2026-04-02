package com.ktdsuniversity.edu.movie.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MovieWriteVO {
	private String movieId;

	@NotBlank(message = "이미지 등록을 해주세요.")
	private String posterUrl;

	@NotBlank(message = "제목을 입력해주세요.")
	private String title;

	@NotBlank(message = "관람 가능 나이를 입력해주세요.")
	@Size(min = 1, max = 3,  message = "입력은 3글 이하로 입력해주세요.")
	private String movieRating;
	private String openDate;
	private String openCountry;
	private int runningTime;
	private String introduce;

	@NotBlank(message = "소개글을 입력해주세요.")
	private String synopsis;
	private String originalTitle;

	@NotBlank(message = "개봉 상태를 입력해주세요.")
	private String state;

	@NotBlank(message = "원본 언어를 입력해주세요.")
	private String language;
	private long budget;
	private long profit;

	// js 값 받아오기
	private List<MultipartFile> attachFile;

	public List<MultipartFile> getAttachFile() {
		return this.attachFile;
	}

	public void setAttachFile(List<MultipartFile> attachFile) {
		this.attachFile = attachFile;
	}

	public String getMovieId() {
		return this.movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getPosterUrl() {
		return this.posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMovieRating() {
		return this.movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}

	public String getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getOpenCountry() {
		return this.openCountry;
	}

	public void setOpenCountry(String openCountry) {
		this.openCountry = openCountry;
	}

	public int getRunningTime() {
		return this.runningTime;
	}

	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getSynopsis() {
		return this.synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getOriginalTitle() {
		return this.originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public long getBudget() {
		return this.budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public long getProfit() {
		return this.profit;
	}

	public void setProfit(long profit) {
		this.profit = profit;
	}

}
