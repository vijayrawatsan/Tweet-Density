package in.xebia.vijay.domain;

import java.util.List;

public class DensityInfoWrapper {
	
	private List<Data> densityData;
	
	private int countReturned;

	public List<Data> getDensityData() {
		return densityData;
	}

	public void setDensityData(List<Data> densityData) {
		this.densityData = densityData;
	}

	public int getCountReturned() {
		return countReturned;
	}

	public void setCountReturned(int countReturned) {
		this.countReturned = countReturned;
	}
	
	
}
