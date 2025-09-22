class Solution {
    public int maxFrequencyElements(int[] nums) {
        // Create a HashMap to store the frequency of each number in the array
        Map<Integer,Integer> hashMap = new HashMap<>();
        
        // Variable to keep track of the highest frequency found so far
        int highest = 0;
        
        // Loop through each number in the array
        for(int num : nums){
            // Get current count of num from hashmap, default is 0, then add 1
            int count = hashMap.getOrDefault(num, 0) + 1;
            
            // Update the frequency of num in the hashmap
            hashMap.put(num, count);
            
            // Update the highest frequency if current count is greater
            highest = Math.max(highest, count);
        }
        
        // Variable to sum the total elements that have the maximum frequency
        int total = 0;
        
        // Loop through all the frequencies in the hashmap
        for(int count : hashMap.values()){
            // If this number's frequency is equal to the highest frequency
            if(count == highest){
                // Add this count to the total
                total += count;
            }
        }
        
        // Return the sum of all elements that have the maximum frequency
        return total;
    }
}
