// Michael Adams
// TuTh 1pm

import java.io.*;
import java.util.*;

public class Assig4
{
	//MAIN
	public static void main(String[] args)
	{
		new Assig4(); //call constructor
	}
	
	//CONSTRUCTOR
	public Assig4()
	{
		//GET INPUTS FROM USER
		int arrSize, numTrials;
		long b = 1000000000;
		Scanner inScan = new Scanner(System.in);
		//get array size
		System.out.println("Enter array size: ");
		arrSize = inScan.nextInt();
		//get number of trials
		System.out.println("Enter number of trials: ");
		numTrials = inScan.nextInt();
		//get output file name
		inScan.nextLine();
		PrintWriter fileOut;
		while (true)
		{
			try
			{
				System.out.println("Enter filename: ");
				String fTemp = inScan.nextLine();
				File fName = new File(fTemp); //this may not work
				fileOut = new PrintWriter(fName);
				break;
			}
			catch (IOException e)
			{
				System.out.println("Didn't work, try again");
			}
		}
		
		//CREATE ARRAYS
		//generate random array
		Random randGen = new Random();
		Integer[] randArr = new Integer[arrSize];
		for (int i = 0; i < arrSize; i++)
		{
			randArr[i] = randGen.nextInt(arrSize);
		}
		//generate sorted array
		Integer[] sortedArr = new Integer[arrSize];
		for (int i = 0; i < arrSize; i++)
		{
			sortedArr[i] = i + 1;
		}
		//generate sorted array
		Integer[] revSortedArr = new Integer[arrSize];
		for (int i = 0; i < arrSize; i++)
		{
			revSortedArr[i] = arrSize - i;
		}
		
		//SORT ARRAYS
		Integer[] tempArr = new Integer[arrSize];
		long start, finish, delta, avgTime;
		double avgTimeSec;
		// QUICKSORT RANDARR -----------------------------------------------------------------
		if (arrSize <= 100000)
		{
			avgTime = 0;
			for (int i = 0; i < numTrials; i++)
			{
				//copy randArr into temp
				if (arrSize <= 20)
					fileOut.print("Before Sort: ");
				for (int j = 0; j < arrSize; j++)
				{
					tempArr[j] = randArr[j];
					//print array if arrSize <= 20
					if (arrSize <= 20)
					{
						fileOut.print(tempArr[j] + " ");
					}
				}
				//call sort
				start = System.nanoTime();
				Quick.quickSort(tempArr, arrSize);
				finish = System.nanoTime();
				delta = finish - start;
				avgTime = avgTime + delta;
				if (arrSize <= 20)
					fileOut.print("After Sort: ");
				if (arrSize <= 20)
				{
					for (int k = 0; k < arrSize; k++)
					{
					fileOut.print(tempArr[k] + " ");
					}
				}
				if (arrSize <= 20)
				fileOut.println();
			}
			// find avg time in seconds
			avgTime = avgTime / (long) numTrials;
			avgTimeSec = avgTime * Math.pow(10,-9);
			//write stuff
			fileOut.println("Algorithm: Simple QuickSort");
			fileOut.println("Array Size: " + arrSize);
			fileOut.println("Order: Random");
			fileOut.println("Number of Trials: " + numTrials);
			fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
			fileOut.println();
			fileOut.println();
		}
		
		// QUICKSORT SORTEDARR --------------------------------------------------
		if (arrSize <= 100000)
		{
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy sortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = sortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			Quick.quickSort(tempArr, arrSize);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
					fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Simple QuickSort");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();
		}
		
		// QUICKSORT REVSORTEDARR --------------------------------------------------
		if (arrSize <= 100000)
		{
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy revSortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = revSortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			Quick.quickSort(tempArr, arrSize);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
					fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Simple QuickSort");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Reverse Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();
		}

		// MEDIAN OF 3 QUICKSORT - BASE CASE < 5 RANDARR -----------------------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy randArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = randArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.quickSort(tempArr, 0, arrSize-1, 5);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
				fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Median of 3 - Base Case < 5");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Random");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();

		// MEDIAN OF 3 QUICKSORT - BASE CASE < 5 SORTEDARR -----------------------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy sortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = sortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.quickSort(tempArr, 0, arrSize-1, 5);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
				fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Median of 3 - Base Case < 5");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();

		// MEDIAN OF 3 QUICKSORT - BASE CASE < 5 REVSORTEDARR -----------------------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy revSortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = revSortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.quickSort(tempArr, 0, arrSize-1, 5);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
				fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Median of 3 - Base Case < 5");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Reverse Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();
		
		// MEDIAN OF 3 QUICKSORT - BASE CASE < 20 RANDARR -----------------------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy randArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = randArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.quickSort(tempArr, 0, arrSize-1, 20);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
				fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Median of 3 - Base Case < 20");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Random");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();

		// MEDIAN OF 3 QUICKSORT - BASE CASE < 20 SORTEDARR -----------------------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy sortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = sortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.quickSort(tempArr, 0, arrSize-1, 20);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
				fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Median of 3 - Base Case < 20");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();

		// MEDIAN OF 3 QUICKSORT - BASE CASE < 20 REVSORTEDARR -----------------------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy revSortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = revSortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.quickSort(tempArr, 0, arrSize-1, 20);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
				fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Median of 3 - Base Case < 20");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Reverse Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();
	
		// MEDIAN OF 3 QUICKSORT - BASE CASE < 100 RANDARR -----------------------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy randArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = randArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.quickSort(tempArr, 0, arrSize-1, 100);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
				fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Median of 3 - Base Case < 100");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Random");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();

		// MEDIAN OF 3 QUICKSORT - BASE CASE < 100 SORTEDARR -----------------------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy sortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = sortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.quickSort(tempArr, 0, arrSize-1, 100);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
				fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Median of 3 - Base Case < 100");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();

		// MEDIAN OF 3 QUICKSORT - BASE CASE < 100 REVSORTEDARR -----------------------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy revSortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = revSortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.quickSort(tempArr, 0, arrSize-1, 100);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
				fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Median of 3 - Base Case < 100");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Reverse Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();
		
		// Random QUICKSORT RANDARR -----------------------------------------------------------------
		int randInt = randGen.nextInt(arrSize);
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy randArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = randArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			QuickRand.quickSort(tempArr, arrSize);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
				fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Random QuickSort");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Random");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();
		
		// Random QUICKSORT SORTEDARR --------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy sortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = sortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			//Quick.quickSort(tempArr, arrSize, randInt);
			QuickRand.quickSort(tempArr, arrSize);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
					fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Random QuickSort");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();
		
		// Random QUICKSORT REVSORTEDARR --------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy revSortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = revSortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			//Quick.quickSort(tempArr, arrSize, randInt);
			QuickRand.quickSort(tempArr, arrSize);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
					fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: Random QuickSort");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Reverse Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();

		// MERGESORT RANDARR -----------------------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy randArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = randArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.mergeSort(tempArr, arrSize);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
				fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: MergeSort");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Random");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();
		
		// MERGESORT SORTEDARR --------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy sortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = sortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.mergeSort(tempArr, arrSize);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
					fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: MergeSort");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();
		fileOut.println();
		
		// MERGESORT REVSORTEDARR --------------------------------------------------
		avgTime = 0;
		for (int i = 0; i < numTrials; i++)
		{
			//copy revSortedArr into temp
			if (arrSize <= 20)
				fileOut.print("Before Sort: ");
			for (int j = 0; j < arrSize; j++)
			{
				tempArr[j] = revSortedArr[j];
				//print array if arrSize <= 20
				if (arrSize <= 20)
				{
					fileOut.print(tempArr[j] + " ");
				}
			}
			//call sort
			start = System.nanoTime();
			TextMergeQuick.mergeSort(tempArr, arrSize);
			finish = System.nanoTime();
			delta = finish - start;
			avgTime = avgTime + delta;
			if (arrSize <= 20)
				fileOut.print("After Sort: ");
			if (arrSize <= 20)
			{
				for (int k = 0; k < arrSize; k++)
				{
					fileOut.print(tempArr[k] + " ");
				}
			}
			if (arrSize <= 20)
			fileOut.println();
		}
		// find avg time in seconds
		avgTime = avgTime / (long) numTrials;
		avgTimeSec = avgTime * Math.pow(10,-9);
		//write stuff
		fileOut.println("Algorithm: MergeSort");
		fileOut.println("Array Size: " + arrSize);
		fileOut.println("Order: Reverse Sorted");
		fileOut.println("Number of Trials: " + numTrials);
		fileOut.printf("Average Time per Trial: %f sec\n", avgTimeSec);
		fileOut.println();


		fileOut.close();
	}
}