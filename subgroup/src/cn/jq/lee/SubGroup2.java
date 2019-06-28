package cn.jq.lee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * ����֮ǰ���AB��
 * 
 */
public class SubGroup2 {
	static List<String> list = new ArrayList<>();
	static List<String> group_A = null;
	static List<String> group_B = new ArrayList<>();
	static Map<String, Integer> map = new HashMap<String, Integer>();
	static List<Integer> exist = new ArrayList<>();
	static int index;
	static int temp;
	static int count = 0;
	static {
		list.add("����");
		list.add("59");
		list.add("����");
		list.add("����");
		list.add("�ϳ�");
		list.add("����");
		list.add("С��");
		list.add("����");
		map.put("59", 0);
		map.put("����", 0);
		map.put("����", 0);
		map.put("�ϳ�", 0);
		map.put("����", 0);
		map.put("����", 0);
		map.put("С��", 0);
		map.put("����", 0);

	}

	public static void main(String[] args) throws InterruptedException {
		while (true) {
			System.out.println("------------------��" + (++count)
					+ "�η���----------------");
			group_A = new ArrayList<String>(Arrays.asList(new String[list
					.size()]));
			Collections.shuffle(list);
			/*
			 * for(int i=0;i<list.size();i++){
			 * System.out.print(list.get(i)+"\t"); }
			 */
			Collections.copy(group_A, list);
			subgroup(list);
			System.out.println("------------------��ǰ�˵�-----------------");
			int random = (int) (Math.random() * 10);
			if (random % 2 == 0) {
				pay(group_A, group_B);
			} else {
				pay(group_B, group_A);
			}
			Set<Entry<String, Integer>> entrySet = map.entrySet();
			Iterator<Entry<String, Integer>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, Integer> next = iterator.next();
				System.out
						.print(next.getKey() + ":" + next.getValue() + "\t\t");
			}
			System.out.println();

			group_B.clear();
			exist.clear();

			Thread.sleep(12345);
		}
	}

	/*
	 * ����
	 */
	private static void subgroup(List<String> list) {
		for (int i = 0; i < list.size() / 2; i++) {
			do {
				index = (int) (Math.random() * list.size());
			} while (exist.contains(index));
			group_B.add(list.get(index));
			
			exist.add(index);
		}
		group_A.removeAll(group_B);
		System.out.print("A�飺\t");
		for (String string : group_A) {
			System.out.print(string + "\t");
		}
		System.out.println();
		System.out.print("B�飺\t");
		for (String string : group_B) {
			System.out.print(string + "\t");
		}
		System.out.println();
	}

	/*
	 * �˵�����
	 */
	private static void pay(List<String> winner, List<String> loser) {
		for (int i = 0; i < winner.size(); i++) {
			temp = map.get(winner.get(i));
			map.put(winner.get(i), temp + 100);
			temp = map.get(loser.get(i));
			map.put(loser.get(i), temp - 100);
		}
	}/*
	 * A�飺 59 �ϳ� С�� ���� B�飺 ���� ���� ���� ���� 59 +100 +100 �ϳ� +100 +100 С�� +100 +100 ����
	 * +100 +100
	 * 
	 * ���� -100 -100 ���� -100 -100 ���� -100 -100 ���� -100 -100
	 * 
	 * A�飺 59 ���� С�� ���� B�飺 ���� ���� �ϳ� ���� 59 +100 +200 �ϳ� -100 0 С�� +100 +200 ����
	 * +100 +200
	 * 
	 * ���� -100 -200 ���� -100 -200 ���� -100 -200 ���� +100 0
	 * 
	 * A�飺 ���� 59 ���� С�� B�飺 �ϳ� ���� ���� ���� 59 +100 +300 �ϳ� -100 -100 С�� +100 +300 ����
	 * -100 +100
	 * 
	 * ���� +100 -100 ���� +100 -100 ���� -100 -300 ���� -100 -100
	 */
}
