package edu.neu.coe.info6205.union_find;

import java.util.Random;
import java.util.Scanner;

public class UF_Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Scanner in = new Scanner(System.in);
		int n = 1;
		while(n!=0) {
			n = in.nextInt();
			System.out.println(count(n));
		}
		
	}
	
	public static int count(int n) {
		UF h1 = new UF_HWQUPC(n);
		
		Random rand = new Random();
		int result = 0;
		
		boolean all_connected = false;
		while(!all_connected) {
			int i = rand.nextInt(n);
			int j = rand.nextInt(n);
			while(i==j) {
				j = rand.nextInt(n);
			}
			if(!h1.isConnected(i,j)) {
				h1.union(i, j);
			}
			if(h1.components()==1) {
				all_connected =true;
			}
			result+=1;
		}
		return result;
	}

}
