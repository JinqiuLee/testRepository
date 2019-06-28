package cn.jq.lee;

import java.util.Scanner;
import java.util.TreeSet;

public class PassRiver {
	public static void main(String[] args) {
		TreeSet<Integer> a = new TreeSet<>();
		TreeSet<Integer> b = new TreeSet<>();
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
		int first;
		int second;
		// ��ȡt1��t2�ĺ�ʱ����Ϊ�����t1��t2���ش�����������ֵ��һֱ���õ�
		first = a.first();
		second = a.higher(a.first());
		// 2*t2��t1+t(n-1)�Ƚ�����t1���ش�������t1��t2���ش�
		if (2 * a.higher(a.first()) < (a.first() + a.lower(a.last()))) {
			// t1��t2���ش�
			while (a.size() > 0) {
				int cur_Size=a.size();
				// ���A��������B����2���ˣ���ôÿ��A��ȥ2���ˣ�ͬʱB��Ҫ���غ�ʱ��С��һ���ˣ������ֻ����t1��t2
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
				// b->a������A��ȥ�������Ļ�����С��һ�飬B���ظ�A����Զ���Ǻ�ʱ���ٵ��Ǹ��ˣ�������һ���ֿ��Թ��ã�û��Ҫд��if�����
				if (cur_Size != 2) {
					a.add(b.first());
					sum += b.first();
					b.remove(b.first());
				}
			}
		} else {
			// t1���ش�
		}
		System.out.println(sum);
	}
}
