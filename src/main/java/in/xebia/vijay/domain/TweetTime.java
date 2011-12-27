package in.xebia.vijay.domain;

import org.codehaus.jackson.annotate.JsonAnySetter;


public class TweetTime {

	private String created_at;

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	@JsonAnySetter
	public void handleUnknown(String key, Object value) {
		// do something: put to a Map; log a warning, whatever
	}

}
