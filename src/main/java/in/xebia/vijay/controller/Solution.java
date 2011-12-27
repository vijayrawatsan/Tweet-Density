package in.xebia.vijay.controller;
class Solution {
    class PermuteResult {
    	private int swapIndex;
    	private boolean swapped;
		public int getSwapIndex() {
			return swapIndex;
		}
		public void setSwapIndex(int swapIndex) {
			this.swapIndex = swapIndex;
		}
		public boolean isSwapped() {
			return swapped;
		}
		public void setSwapped(boolean swapped) {
			this.swapped = swapped;
		}
    }
    public static void main(String args[]) {
    	Solution solution = new Solution();
    	PermuteResult permuteResult = solution.new PermuteResult();
       /* try {
            BufferedReader br = 
              new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            int number = Integer.parseInt(s);
            for(int i=0;i<number;i++)
                System.out.println(returnCount(br.readLine()));

        } catch (Exception e) {
            System.err.println("Error:" + e.getMessage());
        }*/
    	/*Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    	StringBuilder sb = new StringBuilder("");
    	for (int i = 0; i < 20000; i++) {
    		sb.append("ab");
			
		}
    	System.out.println(returnCount(sb.toString()));*/
    	int[] array = new int[]{1,2,3,4};
    	permute(array,0,3);
    	/*System.out.println(array[0]+" "+array[1]+" "+array[2]+" "+array[3]);
    	int swapIndex = 0;
    	for(int i = 0;i<23;i++){
    		swapIndex = getNext(array, swapIndex);
    		System.out.println(array[0]+" "+array[1]+" "+array[2]+" "+array[3]);
    	}*/
    }
    private static int getNext(int[] array,int swapIndex) {
		if(array[swapIndex % array.length]!=array[(swapIndex+1) % array.length])
			swap(swapIndex % array.length, (swapIndex+1) % array.length, array);
		return (swapIndex+1) % array.length;
	}
    
    private static void permute(int[] array, int begin, int end){
    	if(end-begin==1) {
    		swap(begin,end,array);
    		System.out.println(array[0]+" "+array[1]+" "+array[2]+" "+array[3]);
    		return;
    	}
    	else {	
	    	for(int i = begin; i < end; i++){
	    		permute(array,i+1,end);
	    		shift(array,i+1,end);
	    		permute(array,i+1,end);
	    	}
    	}
    }
	
    private static void shift(int[] array, int begin, int end) {
    	int temp = array[end];
    	for(int i = end;i > begin;i--)
    		array[i] = array[i-1];
    	array[begin] = temp;
	}
    
	public static long returnCount(String str) {
    	long time = System.currentTimeMillis();
        long totalCount = str.length();
        int loopMax = str.length();
        int[] matchCountArray = new int[loopMax];
        matchCountArray[0]=loopMax;
        int j = 1;
        for(int i=1;i<loopMax;i++) {
        	int matchCount = 0;
            String stringToCompare = str.substring(i);
            if(str.charAt(0)!=stringToCompare.charAt(0)) {
            	matchCountArray[j]=matchCount;
            	j++;
            	continue;
            }
            matchCount++;
            int stringToCompareLength = stringToCompare.length();
            while(stringToCompareLength > matchCount && str.charAt(matchCount)==stringToCompare.charAt(matchCount)){
            	matchCount++;
            }
            if(matchCount>1 && find(matchCount,matchCountArray)>=0)
            	
            matchCountArray[j]=matchCount;
            j++;
            totalCount+=matchCount;
        }
        System.out.println(System.currentTimeMillis()-time);
        return totalCount;
    }

	private static int find(int matchCount, int[] matchCountArray) {
		for(int i=0;i<matchCountArray.length;i++){
			if(matchCountArray[i]==matchCount)
				return i;
		}
		return -1;
	}
	
	/*private static void getNext(int[] Value){
			int N = Value.length;
		    int i = N - 1;
		    while (Value[i-1] >= Value[i]) 
		      i = i-1;

		    int j = N;
		    while (Value[j-1] <= Value[i-1]) 
		      j = j-1;
		  
		    swap(i-1, j-1, Value);    // swap values at positions (i-1) and (j-1)

		    i++; j = N;
		    while (i < j)
		    {
		      swap(i-1, j-1, Value);
		      i++;
		      j--;
		    }
		    
	}*/

	private static void swap(int i, int j, int[] array) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
