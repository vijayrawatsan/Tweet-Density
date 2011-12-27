package in.xebia.vijay.service;

import in.xebia.vijay.domain.TweetTime;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;


@Service
public class TwitterService {
	
	private static final int TIMEOUT = 10;

	public List<TweetTime> findTweets(String handle, int count, int pageNumber, int timeout)  {
		
		String request = "https://api.twitter.com/1/statuses/user_timeline.json?screen_name="
				+ handle
				+ "&count="
				+ count
				+ "&trim_user=true&page="
				+ pageNumber;
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter("http.socket.timeout", timeout * 1000);
		httpClient.getParams().setParameter("http.connection.timeout", timeout * 1000);
		httpClient.getParams().setParameter("http.connection-manager.timeout", new Long(timeout * 1000));
		httpClient.getParams().setParameter("http.protocol.head-body-timeout", timeout * 1000);
		GetMethod method = new GetMethod(request);
        int statusCode=0;
        List<TweetTime> tweetWithTimes= new ArrayList<TweetTime>();
		try {
			statusCode = httpClient.executeMethod(method);
			if(statusCode!=200)
				throw new HttpException();
	        InputStream rstream = null;
			rstream = method.getResponseBodyAsStream();
		    ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
			tweetWithTimes = mapper.readValue(rstream, new TypeReference<ArrayList<TweetTime>>() {});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    System.out.println("a"+statusCode);
	    return tweetWithTimes;
	}
	
	public List<TweetTime> findTweets(String handle, int count){
		List<TweetTime> tweets = new ArrayList<TweetTime>();
		int pageNumber = (int) Math.ceil(count/(float)200);
		for(int i=1;i<=pageNumber;i++){
			tweets.addAll(findTweets(handle, count, i, TIMEOUT));
		}
		System.out.println(tweets.size());
		return tweets;
	}
}
