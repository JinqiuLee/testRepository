package cn.jq.lee;

import java.util.Arrays;
import java.util.Scanner;

public class PassRiver2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cnt = in.nextInt();
		while (cnt > 0) {
			cnt--;
			int n = in.nextInt();
			int arr[] = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = in.nextInt();
			}
			// 排序
			Arrays.sort(arr);
			int sum = 0;
			// 从大到小一对一对的送走
			for (int i = n - 1; i >= 0; i--) {
				// 判断i的数值做处理
				if (i == 0) {
					sum += arr[0];
					i = -1;
				} else if (i == 1) {
					sum += arr[1];
					i = -1;
				} else if (i == 2) {
					sum += arr[0] + arr[1] + arr[2];
					i = -1;
				} else if (i >= 3) {
					sum += Math.min(arr[0] + 2 * arr[1] + arr[i], 2 * arr[0]
							+ arr[i] + arr[i - 1]);
					i--;
				}
			}
			System.out.println(sum);
		}
	}
}
