package cn.jq.lee;

import java.util.ArrayList;
import java.util.List;

public class SubGroup {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		List<String> group_a = new ArrayList<>();
		List<String> group_B = new ArrayList<>();
		group_a.add("����");
		group_a.add("59");
		group_a.add("����");
		group_a.add("����");
		group_a.add("�ϳ�");
		group_a.add("����");
		group_a.add("С��");
		group_a.add("����");
		int size = group_a.size();
		for (int i = 0; i < size/2; i++) {
			
		}
		System.out.print("A��:\t");
		for (int i = 0; i < group_a.size(); i++) {
			if (i != group_a.size()-1) {
				System.out.print(group_a.get(i) + "\t");
			}else{
				System.out.println(group_a.get(i));
			}
		}
		System.out.print("B��:\t");
		for (int i = 0; i < group_a.size(); i++) {
			System.out.print(group_B.get(i) + "\t");
		}

	}
}
