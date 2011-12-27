package in.xebia.vijay.service;

import in.xebia.vijay.domain.Data;
import in.xebia.vijay.domain.DensityInfoWrapper;
import in.xebia.vijay.domain.TweetTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DensityService {
	
	@Autowired
	private TwitterService twitterService;
	
	public DensityInfoWrapper getDensity(String handle, int count) {
		List<TweetTime> tweets = twitterService.findTweets(handle, count);
		Map<Integer,Integer> densityMap = new HashMap<Integer, Integer>();
		for(int i=0;i<24;i++)
			densityMap.put(i, 0);
		for(TweetTime tweet:tweets){
			Integer keyValue = findHour(tweet.getCreated_at());
			Integer currentCount = densityMap.get(keyValue);
			currentCount++;
			densityMap.put(keyValue, currentCount);
		}
		DensityInfoWrapper densityInfoWrapper = new DensityInfoWrapper();
		densityInfoWrapper.setCountReturned(tweets.size());
		densityInfoWrapper.setDensityData(getDensityData(densityMap));
		return densityInfoWrapper;
	}

	private List<Data> getDensityData(Map<Integer,Integer> densityMap){
		List<Data> densityData = new ArrayList<Data>();
		for(int i=0;i<24;i++) {
			Data data = new Data();
			data.setHour(i);
			data.setCount(densityMap.get(i));
			densityData.add(data);
		}
		return densityData;
	}
	
	private Integer findHour(String date) {
		return Integer.parseInt(date.substring(11, 13));
	}
}
