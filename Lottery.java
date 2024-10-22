package me.antritus.edu.stadinao;

import java.util.*;

public final class Lottery {
	public static int SELECT = 20;
	public static int SELECTABLE = 100;
	private final Sort sort;
	private final Random random;
	private final boolean debug;

	public static void main(String[] args){
		Lottery lottery = new Lottery(Sort.LOW_HIGH,
				new Random(System.nanoTime()*System.nanoTime()*423123*System.currentTimeMillis()),
				false
				);
		System.out.println(lottery.roll());
	}

	public Lottery(Sort sort, Random random, boolean debug) {
		this.sort = sort;
		this.random = random;
		this.debug = debug;

		if (SELECTABLE < SELECT){
			throw new RuntimeException("Cannot roll lottery while select amount of numbers is less than the amount of numbers which can be selected");
		}
	}

	public String roll() {
		int[] numbers = sort.sort(rollNumbers());
		StringBuilder builder = new StringBuilder("Rolling numbers....").append("\n");
		for (int i = 0; i < numbers.length; i++){
			int num = numbers[i];
			if (!debug){
			if (i!=0){
				builder.append(" ");
			}
			} else {
				if (i!=0){
					builder.append("\n");
				}
				builder.append(i+1).append(": ");
			}
			builder.append(num);
		}
		return builder.toString();
	}

	public int[] rollNumbers(){
		int[] numbers = new int[SELECT];
		int selected = 0;
		int randomInt = -1;
		while (selected < SELECT){
			randomInt = random.nextInt(1, SELECTABLE);

			int checkup = 0;
			boolean found = false;
			while (selected!=0 && checkup<selected){
				int select = numbers[checkup];
				if (select == randomInt){
					found = true;
					break;
				}

				checkup++;
			}
			if (found){
				continue;
			}

			numbers[selected] = randomInt;
			selected++;
		}

		return numbers;
	}


	public enum Sort {
		HIGH_LOW {
			@Override
			int[] sort(int... ints) {
				List<Integer> integers = new LinkedList<>();
				for (int i : ints) {
					integers.add(i);
				}

				integers.sort(Integer::compareTo);
				integers.sort(Comparator.reverseOrder());

				int[] returnList = new int[ints.length];
				for (int i = 0; i < integers.size(); i++){
					returnList[i] = integers.get(i);
				}

				return returnList;
			}
		},
		LOW_HIGH {
			@Override
			int[] sort(int... ints) {
				List<Integer> integers = new LinkedList<>();
				for (int i : ints) {
					integers.add(i);
				}

				integers.sort(Integer::compareTo);

				int[] returnList = new int[ints.length];
				for (int i = 0; i < integers.size(); i++){
					returnList[i] = integers.get(i);
				}

				return returnList;
			}
		},
		IGNORE,
		;

		int[] sort(int... ints){
			return ints;
		}
	}
}
