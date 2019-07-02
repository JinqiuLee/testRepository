package cn.jq.lee;

import java.util.Scanner;
import java.util.TreeSet;

public class PassRiver {
	public static void main(String[] args) {
		TreeSet<Integer> a = new TreeSet<Integer>();
		TreeSet<Integer> b = new TreeSet<Integer>();
		Scanner in = new Scanner(System.in);
		System.out.println("注意!仅录入一组整数数据。");
		System.out.println("请输入要录入的数据的个数:");
		int count = in.nextInt();
		int n;
		// 循环录入每个人的耗时，无需按大小顺序，但不可重复
		while (count > 0) {
			count--;
			n = in.nextInt();
			a.add(n);
		}
		int sum = 0;
		int first = -1;
		int second = -1;
		// 获取t1与t2的耗时，因为如果是t1与t2来回传，这两个数值会一直被用到
		if (a.size() >= 2) {
			first = a.first();
			second = a.higher(a.first());
		}
		Object[] array = a.toArray();
		for (count = array.length - 1, n = 0; count > 2; count -= 2, n += 2) {
			sum += (Integer) array[count] + (Integer) array[0];
		}
		if (a.size() != 1) {
			if (n * second > sum) {
				sum = 0;
				// t1与t2来回传
				while (a.size() > 0) {
					// int cur_Size=a.size();
					// 如果A大于2个人，那么每次A过去2个人，同时B还要返回耗时最小的一个人，这个人只能是t1与t2
					// 如果最开始最小的和次小的都在a这边，则t1与t2过河，否则，最大的过河
					if (a.contains(first) & a.contains(second)) {
						// a->b
						b.add(a.first());
						a.remove(a.first());
						sum += a.first();
						b.add(a.first());
						a.remove(a.first());
					} else {
						// a->b
						sum += a.last();
						b.add(a.last());
						a.remove(a.last());
						b.add(a.last());
						a.remove(a.last());
					}
					// b->a，无论A过去的是最大的还是最小的一组，B返回给A的永远都是耗时最少的那个人，所以这一部分可以共用，没必要写到if语句内
					if (a.size() != 0) {
						a.add(b.first());
						sum += b.first();
						b.remove(b.first());
					}
				}
			} else {
				// t1来回传
				sum = 0;
				while (a.size() > 0) {
					b.add(a.first());
					a.remove(a.first());
					sum += a.first();
					b.add(a.first());
					a.remove(a.first());
					if (a.size() != 0) {
						a.add(b.first());
						sum += b.first();
						b.remove(b.first());
					}
				}
			}
		}else{
			sum+=a.first();
			b.add(a.first());
			a.remove(a.first());
		}
		System.out.println(sum);
	}
}
