package in.xebia.vijay.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "datatoreturn")
public class DataToReturn {
	//Deliberately named tweetdensity rather tweetDensity
	private TweetDensity tweetdensity;

	public TweetDensity getTweetdensity() {
		return tweetdensity;
	}

	public void setTweetdensity(TweetDensity tweetdensity) {
		this.tweetdensity = tweetdensity;
	}
	
}
