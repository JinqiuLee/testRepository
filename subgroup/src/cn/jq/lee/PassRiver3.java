package cn.jq.lee;

import java.util.Scanner;
import java.util.TreeSet;

public class PassRiver3 {
	public static void main(String[] args) {
		TreeSet<Integer> a = new TreeSet<>();
		TreeSet<Integer> b = new TreeSet<>();
		Scanner in = new Scanner(System.in);
		System.out.println("ע��!��¼��һ���������ݡ�");
		System.out.println("������Ҫ¼������ݵĸ���:");
		int count = in.nextInt();
		int n;
		while(count>0){
			count--;
			n=in.nextInt();
			a.add(n);
		}
		int sum = 0;
		int first;
		int second;
//		a.add(1);
//		a.add(2);
//		a.add(5);
//		a.add(10);
//		a.add(8);
//		a.add(12);
//		a.add(15);
//		a.add(18);
//		a.add(10);
//		a.add(2);
//		a.add(5);
//		a.add(15);
//		a.add(8);
//		a.add(1);
//		a.add(12);
		first = a.first();
		second = a.higher(a.first());
		// 2*t2��t1+t(n-1)�Ƚ�����t1���ش�������t1��t2���ش�
		if (2 * a.higher(a.first()) < (a.first() + a.lower(a.last()))) {
			while (a.size() > 0) {

				if (a.size() > 2) {
					// ����ʼ��С�ĺʹ�С�Ķ���a��ߣ���t1��t2���ӣ��������Ĺ���
					if (a.first() == first & a.higher(a.first()) == second) {
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
					// b->a
					a.add(b.first());
					sum += b.first();
					b.remove(b.first());
				} else if (a.size() == 2) {
					// a->b
					b.add(a.first());
					a.remove(a.first());
					sum += a.first();
					b.add(a.first());
					a.remove(a.first());
				} else {
					System.out.println("����ߵ�����˵���߼���������");
				}
			}
		}
		System.out.println(sum);

	}
}
