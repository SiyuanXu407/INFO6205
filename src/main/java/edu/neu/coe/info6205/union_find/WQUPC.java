/*
 * Copyright (c) 2017. Phasmid Software
 */
package edu.neu.coe.info6205.union_find;

import java.util.Random;
import java.util.Scanner;

/**
 * Weighted Quick Union with Path Compression
 */
public class WQUPC {
    private final int[] parent;   // parent[i] = parent of i
    private final int[] size;   // size[i] = size of subtree rooted at i
    private int count;  // number of components
    private final int[] height;//height[i] = height of subtree rooted at i

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public WQUPC(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
            height[i] = 1;
        }
    }

    public void show() {
        for (int i = 0; i < parent.length; i++) {
            System.out.printf("%d: %d, %d\n", i, parent[i], size[i]);
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int count() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }
        while (p != root) {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }
        return root;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }
    
    public void union_h(int p, int q) {
    	int rootP = find(p);
    	int rootQ = find(q);
    	if(rootP == rootQ) return;
    	if(height[rootP]<height[rootQ]) {
    		parent[rootQ] = rootQ;
    	}else if(height[rootP]>height[rootQ]) {
    		parent[rootQ] = rootP;
    	}else {
    		height[rootP] = height[rootP]+1;
    	}
    	count--;
    }
    
    public static void main(String[] args ) {  
        
       Scanner in = new Scanner(System.in);
       int n = 1;
       while(n!=0) {
    	   n = in.nextInt();
    	   Random rand = new Random();
    	   WQUPC s1 = new WQUPC(n);
    	   WQUPC s2 = new WQUPC(n);
    	   int result1 = 0;
    	   int result2 = 0;
    	   long startTime1 = System.nanoTime();
    	   boolean all_connected = false;
    	   while(!all_connected) {
    		   int i = rand.nextInt(n);
    		   int j = rand.nextInt(n);
    		   while(i==j) {
    			   j = rand.nextInt(n);
    		   }
    		   if(!s1.connected(i,j)) {
    			   s1.union(i, j);
    		   }
    		   if(s1.count()==1) {
    			   all_connected = true;
    		   }
    		   result1+=1;
    	   }
    	   System.out.println("re1--" + result1);
    	   long endTime1 = System.nanoTime();
    	   System.out.println("a#1 time spend:"+(endTime1-startTime1)+"ns");
    	   System.out.println("a#1 average time spend:" + (endTime1-startTime1)/result1+"ns");
    	   
    	   long startTime2 = System.nanoTime();
    	   all_connected = false;
    	   while(!all_connected) {
    		   int i = rand.nextInt(n);
    		   int j = rand.nextInt(n);
    		   while(i==j) {
    			   j = rand.nextInt(n);
    		   }
    		   if(!s2.connected(i, j)) {
    			   s2.union_h(i, j);
    		   }
    		   if(s2.count()==1) {
    			   all_connected = true;
    		   }
    		   result2+=1;
    	   }
    	   System.out.println("re2--"+result2);
    	   long endTime2 = System.nanoTime();
    	   System.out.println("a#2 time spend:"+(endTime2-startTime2)+"ns");
    	   System.out.println("a#2 average time spend:" + (endTime2-startTime2)/result2+"ns");
       }
    }
       


}
