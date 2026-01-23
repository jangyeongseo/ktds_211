package com.ktdsuniversity.edu.article.management;

import com.ktdsuniversity.edu.member.management.Member; //임폴트

public class Article {

	String title;
//	String authorName;
//	String authorId;
	// 이 두개를 대신해서 Member 를 사용함 - member 에 있기때문에 사용
	Member author;
	String content;

	public Article(String title, Member author, String content) {
		super();
		this.title = title;
		this.author = author;
		this.content = content;
	}

	public void viewArticleDescription() {
		System.out.println("제목 : " + this.title);
		System.out.println("내용 : " + this.content);
//		System.out.println("작성자ID : " + this.author.id);
//		System.out.println("작성자명 : " + this.author.name);
		System.out.println("작성자ID : " + this.author.getId());
		System.out.println("작성자명 : " + this.author.getName());

	}

	public static void main(String[] args) {
		Member author = new Member("superuser", "김홍길");
		Article article = new Article("테스트", author, "내용내용내용내용내용");
		article.viewArticleDescription();

	}

}
