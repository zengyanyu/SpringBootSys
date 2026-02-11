package com.zengyanyu.system.algo;

import java.util.Arrays;

/**
 * Java 实现十大经典排序算法
 */
public class SortAlgorithms {

    public static void main(String[] args) {
        // 测试数组（包含重复、正负值，覆盖常见场景）
        int[] arr = {34, -5, 12, 8, 34, 0, 9, -1, 78, 23};
        System.out.println("原始数组：" + Arrays.toString(arr));

        // 测试不同排序算法（按需取消注释）
        int[] bubbleArr = Arrays.copyOf(arr, arr.length);
        bubbleSort(bubbleArr);
        System.out.println("冒泡排序：" + Arrays.toString(bubbleArr));

        int[] selectArr = Arrays.copyOf(arr, arr.length);
        selectionSort(selectArr);
        System.out.println("选择排序：" + Arrays.toString(selectArr));

        int[] insertArr = Arrays.copyOf(arr, arr.length);
        insertionSort(insertArr);
        System.out.println("插入排序：" + Arrays.toString(insertArr));

        int[] shellArr = Arrays.copyOf(arr, arr.length);
        shellSort(shellArr);
        System.out.println("希尔排序：" + Arrays.toString(shellArr));

        int[] mergeArr = Arrays.copyOf(arr, arr.length);
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("归并排序：" + Arrays.toString(mergeArr));

        int[] quickArr = Arrays.copyOf(arr, arr.length);
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("快速排序：" + Arrays.toString(quickArr));

        int[] heapArr = Arrays.copyOf(arr, arr.length);
        heapSort(heapArr);
        System.out.println("堆排序：" + Arrays.toString(heapArr));

        int[] countArr = Arrays.copyOf(arr, arr.length);
        countingSort(countArr);
        System.out.println("计数排序：" + Arrays.toString(countArr));

        int[] bucketArr = Arrays.copyOf(arr, arr.length);
        bucketSort(bucketArr);
        System.out.println("桶排序：" + Arrays.toString(bucketArr));

        int[] radixArr = Arrays.copyOf(arr, arr.length);
        radixSort(radixArr);
        System.out.println("基数排序：" + Arrays.toString(radixArr));
    }

    // 1. 冒泡排序（稳定，O(n²)）
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        // 外层循环控制排序轮数
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // 优化：标记是否发生交换，提前终止
            // 内层循环比较相邻元素，把最大的元素"冒泡"到末尾
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换元素
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // 无交换说明已排序完成
        }
    }

    // 2. 选择排序（不稳定，O(n²)）
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        // 外层循环确定当前要填充的位置
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // 最小元素的索引
            // 内层循环找剩余元素中的最小值
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换当前位置和最小值位置的元素
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    // 3. 插入排序（稳定，O(n²)）
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        // 从第二个元素开始，逐个插入到前面已排序的序列中
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // 当前要插入的元素
            int j = i - 1;
            // 向前找插入位置，比key大的元素后移
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // 插入元素
        }
    }

    // 4. 希尔排序（不稳定，O(n^1.3)）
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        // 步长逐步缩小（初始为n/2，直到步长为1）
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 按步长进行插入排序
            for (int i = gap; i < n; i++) {
                int key = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > key) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = key;
            }
        }
    }

    // 5. 归并排序（稳定，O(n log n)）
    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2; // 避免溢出
        // 分：递归拆分左右子数组
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        // 合：合并两个有序子数组
        merge(arr, left, mid, right);
    }

    // 归并辅助方法
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 临时数组
        int i = left, j = mid + 1, k = 0;
        // 合并两个有序数组到临时数组
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        // 处理剩余元素
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        // 临时数组拷贝回原数组
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    // 6. 快速排序（不稳定，O(n log n)）
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        // 找基准元素的正确位置
        int pivotIndex = partition(arr, left, right);
        // 递归排序左右子数组
        quickSort(arr, left, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, right);
    }

    // 分区辅助方法（选最右元素为基准）
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // 基准元素
        int i = left - 1; // 小于基准的区域边界
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                // 交换元素，扩大小于基准的区域
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 把基准元素放到正确位置
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        return i + 1;
    }

    // 7. 堆排序（不稳定，O(n log n)）
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        // 构建大顶堆（从最后一个非叶子节点开始）
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // 逐个取出堆顶元素（最大值），放到数组末尾
        for (int i = n - 1; i > 0; i--) {
            // 交换堆顶和当前最后一个元素
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // 对剩余元素重新堆化
            heapify(arr, i, 0);
        }
    }

    // 堆化辅助方法（维护大顶堆性质）
    private static void heapify(int[] arr, int heapSize, int root) {
        int largest = root; // 最大元素索引（初始为根）
        int left = 2 * root + 1; // 左子节点
        int right = 2 * root + 2; // 右子节点

        // 找根、左、右中的最大值
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        // 最大值不是根，交换并递归堆化
        if (largest != root) {
            int temp = arr[root];
            arr[root] = arr[largest];
            arr[largest] = temp;
            heapify(arr, heapSize, largest);
        }
    }

    // 8. 计数排序（稳定，O(n+k)，k为数值范围）
    public static void countingSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        // 找数组中的最大值和最小值（适配负数）
        int min = arr[0], max = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        // 计数数组长度 = 最大值 - 最小值 + 1
        int[] countArr = new int[max - min + 1];
        // 统计每个元素出现次数
        for (int num : arr) {
            countArr[num - min]++;
        }
        // 回填到原数组
        int index = 0;
        for (int i = 0; i < countArr.length; i++) {
            while (countArr[i] > 0) {
                arr[index++] = i + min;
                countArr[i]--;
            }
        }
    }

    // 9. 桶排序（稳定，O(n+k)，k为桶数）
    public static void bucketSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        // 1. 找最大值和最小值
        int min = arr[0], max = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        // 2. 定义桶的数量（这里按数值范围均分，也可自定义）
        int bucketNum = 5;
        int[][] buckets = new int[bucketNum][0];
        // 3. 计算每个元素所属的桶
        int range = (max - min + 1) / bucketNum + 1; // 每个桶的数值范围
        for (int num : arr) {
            int bucketIndex = (num - min) / range;
            // 扩容桶并添加元素
            buckets[bucketIndex] = Arrays.copyOf(buckets[bucketIndex], buckets[bucketIndex].length + 1);
            buckets[bucketIndex][buckets[bucketIndex].length - 1] = num;
        }
        // 4. 对每个桶排序（这里用插入排序，也可用其他排序）
        int index = 0;
        for (int[] bucket : buckets) {
            insertionSort(bucket);
            // 5. 合并桶到原数组
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }

    // 10. 基数排序（稳定，O(n*k)，k为位数）
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        // 处理负数：将所有数转为正数（基数排序默认处理非负数）
        int min = Arrays.stream(arr).min().getAsInt();
        if (min < 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] -= min;
            }
        }
        // 找最大值，确定最大位数
        int max = Arrays.stream(arr).max().getAsInt();
        // 按每一位进行桶排序（个位、十位、百位...）
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSortByExp(arr, exp);
        }
        // 还原负数
        if (min < 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] += min;
            }
        }
    }

    // 基数排序辅助：按指定位数（exp）排序
    private static void countSortByExp(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // 0-9 十个桶

        // 统计当前位的数字出现次数
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }
        // 计算前缀和，确定元素在output中的位置
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        // 从后往前遍历，保证稳定性
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        // 拷贝回原数组
        System.arraycopy(output, 0, arr, 0, n);
    }
}