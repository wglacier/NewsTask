package com.glacier.news.dao;


import com.glacier.news.model.NewsItem;
import com.mongodb.Block;
import com.mongodb.client.model.Filters;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class NewsItemDao {

	private final static String MONGO_URI = "mongodb://user1:$dbca4321~@aws-us-east-1-portal.22.dblayer.com:16934";
	private final static String MONGO_DB = "news";
	private final static String MONGO_COLLECTION = "collection1";
	// http://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/
	
	private MongoCollection<Document> collection;
	{
		MongoClientURI connectionString = new MongoClientURI(MONGO_URI);
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase db = mongoClient.getDatabase(MONGO_DB);
		collection = db.getCollection(MONGO_COLLECTION);
	}

	/**
	 * search the content by keyword
	 * @param searchText
	 * @return
	 */
	public List<NewsItem> searchByKeywork(String searchText) {

		StringBuilder retText = new StringBuilder();
		MongoCursor<Document> cursor = collection.find(Filters.text(searchText)).limit(10).iterator();
		List<NewsItem> retList = new ArrayList<>();
		try {
		    while (cursor.hasNext()) {
		    	Document doc = cursor.next();
		    	NewsItem item = constructItem(doc);
		    	retList.add(item);
		    	//retText.append(cursor.next().toJson()).append("\n\n");
		    }
		} finally {
		    cursor.close();
		}
		return retList;
	}

	/**
	 * construct NewsItem from the retrieved MongoDB Document
	 * @param doc
	 * @return
	 */
	private NewsItem constructItem(Document doc) {
		NewsItem item = new NewsItem();
		item.setAuthor(doc.getString("author"));
		item.setUrl(doc.getString("url"));
		item.setTitle(doc.getString("title"));
		item.setLast_updated(doc.getString("last_updated"));
		item.setContent(doc.getString("content").substring(0, 100));
		return item;
	}
}