package edu.neu.coe.info6205.util;

import java.util.Arrays;
import java.util.Random;
import edu.neu.coe.info6205.sort.simple.InsertionSort;

public class Main {
	private static Random random = new Random(System.currentTimeMillis());

	public static Integer[] getRandomArray(int size) {
		Integer[] array = new Integer[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(size * 10);
		}
		return array;
	}

	/**
	 * Implement a main program (or you could do it via your own unit tests) to
	 * actually run the following benchmarks: measure the running times of this
	 * sort, using four different initial array ordering situations: random,
	 * ordered, partially-ordered and reverse-ordered. I suggest that your arrays to
	 * be sorted are of type Integer. Use the doubling method for choosing n and
	 * test for at least five values of n. Draw any conclusions from your
	 * observations regarding the order of growth.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 10000;
 
		size=1000;
		// prepare 4 types of array
		Integer[] a_random = getRandomArray(size);

		Integer[] a_ordered = Arrays.copyOf(a_random, a_random.length);
		Arrays.sort(a_ordered);

		Integer[] a_partially_ordered = Arrays.copyOf(a_random, a_random.length);
		Arrays.sort(a_partially_ordered, 0, size / 2);

		Integer[] a_reverse_ordered = Arrays.copyOf(a_random, a_random.length);
		Arrays.sort(a_reverse_ordered, (a, b) -> b.compareTo(a));

		//System.out.println(Arrays.toString(a_random));
		//System.out.println(Arrays.toString(a_ordered));
		//System.out.println(Arrays.toString(a_partially_ordered));
		//System.out.println(Arrays.toString(a_reverse_ordered));

		System.out.println("N\tRandom\t\tOrdered\t\tPartiallyOrdered\tReverseOrdered");
		
		int n = 100;
		InsertionSort<Integer> sort = new InsertionSort<Integer>();
		for (int i = 0; i < 5; i++) {

			System.out.print(n + "\t");

			sort.sort(a_random, 0, a_random.length);
 
			double t1 = new Timer().repeat(n, ()->a_random, (a)->sort.sort(a), null, null);
			double t2 = new Timer().repeat(n, ()->a_ordered, (a)->sort.sort(a), null, null);
			double t3 = new Timer().repeat(n, ()->a_partially_ordered, (a)->sort.sort(a), null, null);
			double t4 = new Timer().repeat(n, ()->a_reverse_ordered, (a)->sort.sort(a), null, null);
			
			System.out.println(String.format("%.6f\t%.6f\t%.6f\t\t%.6f", t1, t2, t3, t4));
			n *= 2;
		}
	}

}
