package cn.jq.lee;


import java.util.TreeSet;

public class PassRiver {
	public static void main(String[] args) {
		TreeSet<Integer> a = new TreeSet<>();
		TreeSet<Integer> b = new TreeSet<>();
		int sum = 0;
		int count = 0;
		a.add(10);
		a.add(2);
		a.add(5);
		a.add(15);
		a.add(8);
		a.add(1);
		a.add(20);
		if ((a.first() + a.floor(a.last()) - 2 * a.ceiling(a.first())) > 0) {
			while (a.size() > 0) {
				if (count %2==1) {
					if (a.size() > 3) {
						b.add(a.first());
						a.remove(a.first());
						sum += a.first();
						b.add(a.first());
						a.remove(a.first());
						a.add(b.first());
						sum += b.first();
						b.remove(b.first());
					} else {
						if (a.size() != 2) {
							b.add(a.first());
							a.remove(a.first());
							sum += a.first();
							b.add(a.first());
							a.remove(a.first());
							a.add(b.first());
							sum+=b.first();
							b.remove(b.first());
							//break;
						} else {
							b.add(a.last());
							sum += a.last();
							a.remove(a.last());
							b.add(a.last());
							a.remove(a.first());
						}
					}
				} else {
					b.add(a.last());
					sum += a.last();
					a.remove(a.last());
					b.add(a.last());
					a.remove(a.last());
					sum+=b.first();
					a.add(b.first());
					b.remove(b.first());
				}
				count++;
			}
		}
		System.out.println(sum);
	}
}
