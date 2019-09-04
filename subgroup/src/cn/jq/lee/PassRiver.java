package cn.jq.lee;

import java.util.Scanner;
import java.util.TreeSet;

public class PassRiver {
	static TreeSet<Integer> a = new TreeSet<Integer>();
	static TreeSet<Integer> b = new TreeSet<Integer>();
	static int sum = 0;
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("注意!仅录入一组整数数据。");
		System.out.println("请输入要录入的数据的个数:");
		int count = in.nextInt();
		int a_b_1=-1;
		int a_b_2=-1;
		int n;
		// 循环录入每个人的耗时，无需按大小顺序，但不可重复
		while (count > 0) {
			count--;
			n = in.nextInt();
			a.add(n);
		}
		int first = -1;
		int second = -1;
		// 获取t1与t2的耗时，因为如果是t1与t2来回传，这两个数值会一直被用到
		if (a.size() >= 2) {
			first = a.first();
			second = a.higher(a.first());

			Object[] array = a.toArray();
			for (count = array.length - 1, n = 0; count > 2; count -= 2, n += 2) {
				sum += (Integer) array[count-1] + (Integer) array[0];
			}
			if (n * second < sum) {
				sum = 0;
				// t1与t2来回传
				while (a.size() > 0) {
					// int cur_Size=a.size();
					// 如果A大于2个人，那么每次A过去2个人，同时B还要返回耗时最小的一个人，这个人只能是t1与t2
					// 如果最开始最小的和次小的都在a这边，则t1与t2过河，否则，最大的过河
					if (a.contains(first) & a.contains(second)) {
						// a->b
						a_b_1=a.first();
						a_b_2=a.higher(a_b_1);
					} else {
						// a->b
						a_b_1=a.last();
						a_b_2=a.lower(a_b_1);
					}
					// b->a，无论A过去的是最大的还是最小的一组，B返回给A的永远都是耗时最少的那个人，所以这一部分可以共用，没必要写到if语句内
					move(a_b_1,a_b_2,a);
				}
			} else {
				// t1来回传
				sum = 0;
				while (a.size() > 0) {
					a_b_1=a.first();
					a_b_2=a.higher(a_b_1);
					move(a_b_1,a_b_2,a);
				}
			}
		} else if (a.size() == 1) {
			sum += a.first();
			b.add(a.first());
			a.remove(a.first());
		} else {
			System.out.println("可能没有输入任何东西");
		}
		System.out.println(sum);
	}
	private static void move(int a_b_1,int a_b_2,TreeSet<Integer> a) {
		int max=(a_b_1>a_b_2)?a_b_1:a_b_2;
		b.add(a_b_1);
		a.remove(a_b_1);
		b.add(a_b_2);
		a.remove(a_b_2);
		sum+=max;
		if (a.size() != 0) {
			a.add(b.first());
			sum += b.first();
			b.remove(b.first());
		}
		
	}
}
