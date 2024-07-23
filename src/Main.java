import java.util.*;


class Solution {

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> frequency = getFrequency(nums);
        return finish(frequency, nums.length);
    }

    public Map<Integer, Integer> getFrequency(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        return frequency;
    }

    public int[] finish(Map<Integer, Integer> frequency, int size) {
        int[] sorted = new int[size];
        int index = 0;
        while (frequency.containsValue(1)) {
            int largest = -101;
            for (Map.Entry<Integer, Integer> ent : frequency.entrySet()) {
                if (ent.getValue() == 1 && ent.getKey() > largest) {
                    largest = ent.getKey();
                }
            }
            sorted[index++] = largest;
            frequency.remove(largest);
        }
            while (!frequency.isEmpty()) {
                int value = 101;
                int key = 101;
                for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
                    if (entry.getValue() < value) {
                        key = entry.getKey();
                        value = entry.getValue();
                    }
                    if (entry.getValue() == value && entry.getKey() > key) {
                        key = entry.getKey();
                    }
                }
                for (int i = index; i < value + index; i++) {
                    sorted[i] = key;
                }
                index += frequency.get(key);
                frequency.remove(key);
            }
        return sorted;
    }
}