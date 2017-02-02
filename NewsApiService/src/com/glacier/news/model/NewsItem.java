package com.glacier.news.model;



public class NewsItem {

	private Long id;
	private String url;
	private String author;
	private String title;
	private String content;
	private String last_updated;

	public NewsItem(long id, String author, String content, String url, String last_updated, String title) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
		this.url = url;
		this.last_updated = last_updated;
	}

	public NewsItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
}