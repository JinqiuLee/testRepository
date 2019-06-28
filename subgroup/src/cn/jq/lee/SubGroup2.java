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
 * 分组之前清空AB组
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
		list.add("陈亮");
		list.add("59");
		list.add("温柔");
		list.add("阿发");
		list.add("老仇");
		list.add("二海");
		list.add("小奎");
		list.add("李永");
		map.put("59", 0);
		map.put("温柔", 0);
		map.put("阿发", 0);
		map.put("老仇", 0);
		map.put("陈亮", 0);
		map.put("二海", 0);
		map.put("小奎", 0);
		map.put("李永", 0);

	}

	public static void main(String[] args) throws InterruptedException {
		while (true) {
			System.out.println("------------------第" + (++count)
					+ "次分组----------------");
			group_A = new ArrayList<String>(Arrays.asList(new String[list
					.size()]));
			Collections.shuffle(list);
			/*
			 * for(int i=0;i<list.size();i++){
			 * System.out.print(list.get(i)+"\t"); }
			 */
			Collections.copy(group_A, list);
			subgroup(list);
			System.out.println("------------------当前账单-----------------");
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
	 * 分组
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
		System.out.print("A组：\t");
		for (String string : group_A) {
			System.out.print(string + "\t");
		}
		System.out.println();
		System.out.print("B组：\t");
		for (String string : group_B) {
			System.out.print(string + "\t");
		}
		System.out.println();
	}

	/*
	 * 账单计算
	 */
	private static void pay(List<String> winner, List<String> loser) {
		for (int i = 0; i < winner.size(); i++) {
			temp = map.get(winner.get(i));
			map.put(winner.get(i), temp + 100);
			temp = map.get(loser.get(i));
			map.put(loser.get(i), temp - 100);
		}
	}/*
	 * A组： 59 老仇 小奎 李永 B组： 陈亮 温柔 阿发 二海 59 +100 +100 老仇 +100 +100 小奎 +100 +100 李永
	 * +100 +100
	 * 
	 * 陈亮 -100 -100 温柔 -100 -100 阿发 -100 -100 二海 -100 -100
	 * 
	 * A组： 59 二海 小奎 李永 B组： 阿发 陈亮 老仇 温柔 59 +100 +200 老仇 -100 0 小奎 +100 +200 李永
	 * +100 +200
	 * 
	 * 陈亮 -100 -200 温柔 -100 -200 阿发 -100 -200 二海 +100 0
	 * 
	 * A组： 陈亮 59 温柔 小奎 B组： 老仇 李永 阿发 二海 59 +100 +300 老仇 -100 -100 小奎 +100 +300 李永
	 * -100 +100
	 * 
	 * 陈亮 +100 -100 温柔 +100 -100 阿发 -100 -300 二海 -100 -100
	 */
}
