package cn.jq.lee;

import java.util.Scanner;
import java.util.TreeSet;

public class PassRiver {
	static TreeSet<Integer> a = new TreeSet<Integer>();
	static TreeSet<Integer> b = new TreeSet<Integer>();
	static int sum = 0;
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("ע��!��¼��һ���������ݡ�");
		System.out.println("������Ҫ¼������ݵĸ���:");
		int count = in.nextInt();
		int a_b_1=-1;
		int a_b_2=-1;
		int n;
		// ѭ��¼��ÿ���˵ĺ�ʱ�����谴��С˳�򣬵������ظ�
		while (count > 0) {
			count--;
			n = in.nextInt();
			a.add(n);
		}
		int first = -1;
		int second = -1;
		// ��ȡt1��t2�ĺ�ʱ����Ϊ�����t1��t2���ش�����������ֵ��һֱ���õ�
		if (a.size() >= 2) {
			first = a.first();
			second = a.higher(a.first());

			Object[] array = a.toArray();
			for (count = array.length - 1, n = 0; count > 2; count -= 2, n += 2) {
				sum += (Integer) array[count-1] + (Integer) array[0];
			}
			if (n * second < sum) {
				sum = 0;
				// t1��t2���ش�
				while (a.size() > 0) {
					// int cur_Size=a.size();
					// ���A����2���ˣ���ôÿ��A��ȥ2���ˣ�ͬʱB��Ҫ���غ�ʱ��С��һ���ˣ������ֻ����t1��t2
					// ����ʼ��С�ĺʹ�С�Ķ���a��ߣ���t1��t2���ӣ��������Ĺ���
					if (a.contains(first) & a.contains(second)) {
						// a->b
						a_b_1=a.first();
						a_b_2=a.higher(a_b_1);
					} else {
						// a->b
						a_b_1=a.last();
						a_b_2=a.lower(a_b_1);
					}
					// b->a������A��ȥ�������Ļ�����С��һ�飬B���ظ�A����Զ���Ǻ�ʱ���ٵ��Ǹ��ˣ�������һ���ֿ��Թ��ã�û��Ҫд��if�����
					move(a_b_1,a_b_2,a);
				}
			} else {
				// t1���ش�
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
			System.out.println("����û�������κζ���");
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
