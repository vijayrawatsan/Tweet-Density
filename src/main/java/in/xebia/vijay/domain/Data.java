package in.xebia.vijay.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class Data {
	private int count;
	private int hour;
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
