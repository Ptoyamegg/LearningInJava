package xyz.dyk.DSA;

import java.util.Arrays;

public class Sort {
    //  插入排序法
    public int[] insert_sort(int[] before) {
        if (before.length <= 1) return before;
        int key, j;
        for (int i = 1; i < before.length; i++) {
            key = before[i];
            j = i - 1;
            while (j >= 0 && key < before[j]) {
                before[j + 1] = before[j];
                j--;
            }
            before[j + 1] = key;
        }
        return before;
    }

    //  分治法，归并排序法
    public int[] merge(int[] input) {
        int[] result = new int[input.length];
        int[] left = Arrays.copyOfRange(input, 0, (input.length + 1) / 2);
        int[] right = Arrays.copyOfRange(input, (input.length + 1) / 2, input.length);
        if (left.length > 1) left = merge(left);
        if (right.length > 1) right = merge(right);
        int i = 0, j = 0;
        for (int k = 0; k < input.length; k++) {
            if (left.length == i) {
                result[k] = right[j];
                j++;
                continue;
            }
            if (right.length == j) {
                result[k] = left[i];
                i++;
                continue;
            }
            if (left[i] < right[j]) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                j++;
            }
        }
        return result;
    }

    public void violence_subarray(int[] input) {
        int max, x = 0, y = 0;
        max = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = i; j < input.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += input[k];
                }
                if (max < sum) {
                    max = sum;
                    x = i + 1;
                    y = j + 1;
                }
            }
        }
        System.out.println("max = " + max);
        System.out.println("from " + x + " to " + y);
    }
    public int[] find_max_crossing_subarray(int[] input) {
        return find_max_crossing_subarray(input, 0, input.length);
    }
    public int[] find_max_crossing_subarray(int[] input, int low, int high) {
        if ((high-1) == low) return new int[] { input[low], low, low};
        int middle = (high + low)/2;
        int max = 0, sum = 0, temp = 0, x = low, y = high;
        for (int i = middle - 1; i >= low; i--) {
            sum += input[i];
            if (temp < sum) {
                temp = sum;
                x = i + 1;
            }
        }
        sum = 0;
        max = input[middle] + temp;
        temp = 0;
        for (int i = middle + 1; i < high; i++) {
            sum += input[i];
            if (temp < sum) {
                temp = sum;
                y = i + 1;
            }
        }
        max = temp + max;
        if (max < 0) max = 0;
        int[] right_data = null, left_data = null;
        if ((middle - low) > 1) left_data = find_max_crossing_subarray(input, low, middle);
        if ((high - middle - 1)> 1) right_data = find_max_crossing_subarray(input, middle + 1, high);
        if (right_data == null) right_data = new int[3];
        if (left_data == null) left_data = new int[3];
        if (max >= left_data[0] && max >= right_data[0]) return new int[] { max, x, y};
        if (left_data[0] > right_data[0]) return left_data;
        if (right_data[0] > left_data[0])return right_data;
        return null;
    }

    static int[] input = {5, 2, 4, 6, 1, 3};
    static int[] input2 = {31, 41, 59, 26, 41, 58};
    static int[] input3 = {33, 41, 59, 26, 43, 58, 44, 3, 76, 36};
    static int[] input4 = {31, 41, 59};
    static int[] input5 = {31};
    static int[] input6 = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

    public static void main(String[] args) {
        Sort sort = new Sort();
//        System.out.println(Arrays.toString(sort.merge(input2)));
        //System.out.println(Arrays.toString(sort.insert_sort(input2)));
        //sort.violence_subarray(input6);
        System.out.println(Arrays.toString(sort.find_max_crossing_subarray(input6)));
    }
}
