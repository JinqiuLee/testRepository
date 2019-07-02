package cn.jq.lee;

import java.util.Scanner;
import java.util.TreeSet;

public class PassRiver {
	public static void main(String[] args) {
		TreeSet<Integer> a = new TreeSet<Integer>();
		TreeSet<Integer> b = new TreeSet<Integer>();
		Scanner in = new Scanner(System.in);
		System.out.println("ע��!��¼��һ���������ݡ�");
		System.out.println("������Ҫ¼������ݵĸ���:");
		int count = in.nextInt();
		int n;
		// ѭ��¼��ÿ���˵ĺ�ʱ�����谴��С˳�򣬵������ظ�
		while (count > 0) {
			count--;
			n = in.nextInt();
			a.add(n);
		}
		int sum = 0;
		int first = -1;
		int second = -1;
		// ��ȡt1��t2�ĺ�ʱ����Ϊ�����t1��t2���ش�����������ֵ��һֱ���õ�
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
				// t1��t2���ش�
				while (a.size() > 0) {
					// int cur_Size=a.size();
					// ���A����2���ˣ���ôÿ��A��ȥ2���ˣ�ͬʱB��Ҫ���غ�ʱ��С��һ���ˣ������ֻ����t1��t2
					// ����ʼ��С�ĺʹ�С�Ķ���a��ߣ���t1��t2���ӣ��������Ĺ���
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
					// b->a������A��ȥ�������Ļ�����С��һ�飬B���ظ�A����Զ���Ǻ�ʱ���ٵ��Ǹ��ˣ�������һ���ֿ��Թ��ã�û��Ҫд��if�����
					if (a.size() != 0) {
						a.add(b.first());
						sum += b.first();
						b.remove(b.first());
					}
				}
			} else {
				// t1���ش�
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
