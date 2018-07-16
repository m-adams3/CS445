// Michael Adams
// TuTh 2pm

import java.util.*;

public class MultiDS<T> implements PrimQ<T>, Reorder
{
	private T [] arr;
	private T temp1, temp2, item;
	private int logLen, oldIndex;
	
	//Constructor Methods
	public MultiDS(int size)
	{
		@SuppressWarnings("unchecked")	
		T[] temp = (T []) new Object[size];
		arr = temp;
		logLen = 0;
		oldIndex = 0;
	}
	
	//PrimQ Methods ----------------------------------------------------------------------
	
	// Add a new Object to the PrimQ<T> in the next available location.  If
	// all goes well, return true.  If there is no room in the PrimQ for
	// the new item, return false (you should NOT resize it)
	public boolean addItem(T item)
	{
		if (logLen < arr.length) {
			arr[oldIndex + logLen] = item;
			logLen++;
			return true;
		}
		else
			return false;		
	}
	
	// Remove and return the "oldest" item in the PrimQ.  If the PrimQ
	// is empty, return null.
	public T removeItem()
	{
		//System.out.print("oldIndex: " + oldIndex + " logLen: " + logLen);
		if (logLen > 0) {
			item = arr[0];
			shiftLeft();
			//arr[0] = null;
			logLen--;
			//System.out.print("oldIndex: " + oldIndex + " logLen: " + logLen);
			return item;
		}
		else
			return null;
		
		
	}
	
	// Return true if the PrimQ is full, and false otherwise
	public boolean full()
	{
		return (logLen == arr.length);
	}
	
	// Return true if the PrimQ is empty, and false otherwise
	public boolean empty()
	{
		return (logLen == 0);
	}
	
	// Return the number of items currently in the PrimQ
	public int size()
	{
		return logLen;
	}

	// Reset the PrimQ to empty status by reinitializing the variables
	// appropriately
	public void clear()
	{
		@SuppressWarnings("unchecked")	
		T[] temp = (T []) new Object[arr.length];
		arr = temp;
		logLen = 0;
	}
	
	//Reorder Methods --------------------------------------------------------------------
	
	// Logically reverse the data in the Reorder object so that the item
	// that was logically first will now be logically last and vice
	// versa.  The physical implementation of this can be done in 
	// many different ways, depending upon how you actually implemented
	// your physical MultiDS<T> class
	public void reverse()
	{
		int j = logLen - 1;
	
		//System.out.println("arr[0] is " + arr[0]);
		@SuppressWarnings("unchecked")	
		T[] temp = (T []) new Object[arr.length];
		
		for (int i = 0; i < logLen; i++) {
			temp[j] = arr[i];
			//System.out.println("temp[" + j + "] is " + temp[j] + " and arr[" + i + "] is " + arr[i]);
			j--;
		}
		//System.out.println("arr[0] is " + arr[0]);
		//System.out.println("temp[0] is " + temp[0]);
		arr = temp;
		//System.out.println("arr[0] is " + arr[0]);
	}

	// Remove the logical last item of the DS and put it at the 
	// front.  As with reverse(), this can be done physically in
	// different ways depending on the underlying implementation.  
	public void shiftRight()
	{
		temp1 = arr[logLen-1]; //should be the last obj in the array
		
		for (int i = (arr.length-2); i >= 0; i--) {
			arr[i + 1] = arr[i];
		}
		
		arr[0] = temp1;		
	}

	// Remove the logical first item of the DS and put it at the
	// end.  As above, this can be done in different ways.
	public void shiftLeft()
	{
		temp1 = arr[0];
		
		for (int i = 1; i < arr.length; i++) {
			arr[i - 1] = arr[i];
		}
		
		arr[logLen - 1] = temp1;		
	}
	
	
	// Reorganize the items in the object in a pseudo-random way.  The exact
	// way is up to you but it should utilize a Random object (see Random in 
	// the Java API).  Thus, after this operation the "oldest" item in the
	// DS could be arbitrary.
	public void shuffle()
	{
		/*
		int index, temp;
		Random random = new Random();
		
		for (int i = arr.length - 1; i > 0; i--) {
        index = random.nextInt(i + 1);
        temp2 = arr[index];
        arr[index] = arr[i];
		System.out.println("arr[" + index + "] is " + arr[index] + " and arr[" + i + "] is " + arr[i]);
        arr[i] = temp2;
		}
		*/
		Random rand = new Random();
		for (int i = 0; i < logLen; i++) 
		{
		    int randPos = rand.nextInt(logLen);
		    T temp3 = arr[i];
		    arr[i] = arr[randPos];
		    arr[randPos] = temp3;
		}
	}
	
	public String toString()
	{
		//System.out.println("Contents: ");
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < logLen; i++)
		{
          b.append(arr[i] + " ");
		}
        return b.toString();
	}
}