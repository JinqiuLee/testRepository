package cn.jq.lee;

import java.util.ArrayList;
import java.util.List;

public class SubGroup {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		List<String> group_a = new ArrayList<>();
		List<String> group_B = new ArrayList<>();
		group_a.add("陈亮");
		group_a.add("59");
		group_a.add("温柔");
		group_a.add("阿发");
		group_a.add("老仇");
		group_a.add("二海");
		group_a.add("小奎");
		group_a.add("李永");
		int size = group_a.size();
		for (int i = 0; i < size/2; i++) {
			
		}
		System.out.print("A组:\t");
		for (int i = 0; i < group_a.size(); i++) {
			if (i != group_a.size()-1) {
				System.out.print(group_a.get(i) + "\t");
			}else{
				System.out.println(group_a.get(i));
			}
		}
		System.out.print("B组:\t");
		for (int i = 0; i < group_a.size(); i++) {
			System.out.print(group_B.get(i) + "\t");
		}

	}
}
