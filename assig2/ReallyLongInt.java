// Michael Adams
// TuTh 1pm
// CS 0445 Spring 2017

public class ReallyLongInt extends LinkedListPlus<Integer> implements Comparable<ReallyLongInt>
{
	// Instance variables are inherited.  You may not add any new instance variables
	
	// Default constructor
	private ReallyLongInt()
	{
		super();
	}

	// Note that we are adding the digits here in the FRONT. This is more efficient
	// (no traversal is necessary) and results in the LEAST significant digit first
	// in the list.  It is assumed that String s is a valid representation of an
	// unsigned integer with no leading zeros.
	public ReallyLongInt(String s)
	{
		super();
		char c;
		int digit;
		// Iterate through the String, getting each character and converting it into
		// an int.  Then make an Integer and add at the front of the list.  Note that
		// the add() method (from A2LList) does not need to traverse the list since
		// it is adding in position 1.  Note also the the author's linked list
		// uses index 1 for the front of the list.
		for (int i = 0; i < s.length(); i++)
		{
			c = s.charAt(i);
			if (('0' <= c) && (c <= '9'))
			{
				digit = c - '0';
				this.add(1, new Integer(digit));
			}
			else throw new NumberFormatException("Illegal digit " + c);
		}
	}

	// Simple call to super to copy the nodes from the argument ReallyLongInt
	// into a new one.
	public ReallyLongInt(ReallyLongInt rightOp)
	{
		super(rightOp);
	}
	
	// Method to put digits of number into a String.  Since the numbers are
	// stored "backward" (least significant digit first) we first reverse the
	// number, then traverse it to add the digits to a StringBuilder, then
	// reverse it again.  This seems like a lot of work, but given the
	// limitations of the super classes it is what we must do.
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		if (numberOfEntries > 0)
		{
			this.reverse();
			for (Node curr = firstNode; curr != null; curr = curr.next)
			{
				sb.append(curr.data);
			}
			this.reverse();
		}
		return sb.toString();
	}

	// You must implement the methods below.  See the descriptions in the
	// assignment sheet

	public ReallyLongInt add(ReallyLongInt rightOp)
	{
		ReallyLongInt sumList = new ReallyLongInt();
		
		Node tempNode1 = this.firstNode;
		Node tempNode2 = rightOp.firstNode;
		int carry = 0;
		int tempSum = 0;
		
		while (tempNode1 != null || tempNode2 != null) //while at least one num isnt done
		{
			while (tempNode1 != null && tempNode2 != null) //while both have vals
			{
				tempSum = tempNode1.getData() + tempNode2.getData() + carry; //get tempSum
				if (tempSum < 10)
				{
					sumList.add(tempSum);
					carry = 0;
				}
				else
				{
					sumList.add(tempSum - 10);
					carry = 1;
				}
				tempNode1 = tempNode1.next;
				tempNode2 = tempNode2.next;
				//System.out.print(tempSum + " ");
			}
			while (tempNode1 == null && tempNode2 != null) //while only first has num
			{
				tempSum = 0 + tempNode2.getData() + carry; //get tempSum
				if (tempSum < 10)
				{
					sumList.add(tempSum);
					carry = 0;
				}
				else
				{
					sumList.add(tempSum - 10);
					carry = 1;
				}
				//tempNode1 = tempNode1.next;
				tempNode2 = tempNode2.next;
			}
			while (tempNode1 != null && tempNode2 == null) //while only second has num
			{
				tempSum = tempNode1.getData() + 0 + carry; //get tempSum
				if (tempSum < 10)
				{
					sumList.add(tempSum);
					carry = 0;
				}
				else
				{
					sumList.add(tempSum - 10);
					carry = 1;
				}
				tempNode1 = tempNode1.next;
				//tempNode2 = tempNode2.next;
			}
		}
		
		if (carry == 1) //check for final carry
			sumList.add(1); 
		
		return sumList;
	}
	
	public ReallyLongInt multiply(ReallyLongInt rightOp) //just call add a bunch of times
	{
		String str = rightOp.toString();
		int val = Integer.parseInt(str);
		ReallyLongInt prodList = new ReallyLongInt();
		
		for (int i = 0; i < val; i++)
			prodList = prodList.add(this);
		
		return prodList;
	}
	
	public ReallyLongInt subtract(ReallyLongInt rightOp)
	{
		ReallyLongInt diffList = new ReallyLongInt();
		
		Node tempNode1 = this.firstNode;
		Node tempNode2 = rightOp.firstNode;
		int carry = 0;
		int tempDiff = 0;
		int zeroCounter = 0;
		
		if (this.compareTo(rightOp) < 0) //throw exception if arg is bigger than this
		{
			throw new java.lang.ArithmeticException("Invalid Difference -- Negative Number");
		}
		else //too lazy to indent all this
		{
		while (tempNode1 != null || tempNode2 != null) //while at least one num isnt done
		{
			while (tempNode1 != null && tempNode2 != null)
			{
				tempDiff = tempNode1.getData() - tempNode2.getData() + carry; //get diff
				if (tempDiff > 0)
				{
					diffList.add(tempDiff);
					carry = 0;
					zeroCounter = 0;
					
				}
				else if (tempDiff < 0)
				{
					diffList.add(tempDiff + 10);
					carry = -1;
					zeroCounter = 0;
				}
				else
				{
					diffList.add(0);
					zeroCounter++;
				}
				
				tempNode1 = tempNode1.next;
				tempNode2 = tempNode2.next;
				//System.out.print(tempSum + " ");
			}
			while (tempNode1 == null && tempNode2 != null)
			{
				//this case shouldnt exist because we check number length
			}
			while (tempNode1 != null && tempNode2 == null)
			{
				tempDiff = tempNode1.getData() + 0 + carry; //get diff
				if (tempDiff > 0)
				{
					diffList.add(tempDiff);
					carry = 0;
					zeroCounter = 0;
				}
				else if (tempDiff < 0)
				{
					diffList.add(tempDiff + 10);
					carry = -1;
					zeroCounter = 0;
				}
				else
				{
					diffList.add(0);
					zeroCounter++;
				}
				tempNode1 = tempNode1.next;
				//tempNode2 = tempNode2.next;
			}
		}
		
		if (zeroCounter != 0) //remove leading zeros
			diffList.rightShift(zeroCounter);
		
		return diffList;
		}
	}

	public int compareTo(ReallyLongInt rOp)
	{
		if (this.getLength() > rOp.getLength()) //if current longer than arg
			return 1;
		else if (this.getLength() < rOp.getLength()) //if arg longer than current
			return -1;
		else
		{
			this.reverse(); //reverse current data
			rOp.reverse(); //reverse arg
			Node tempNode1 = this.firstNode;
			Node tempNode2 = rOp.firstNode;
			while (tempNode1.getData().equals(tempNode2.getData()) && tempNode1.next != null)
			{
				tempNode1 = tempNode1.next;
				tempNode2 = tempNode2.next;
			}
			if (tempNode1.getData() > tempNode2.getData())
			{
				this.reverse();
				rOp.reverse();
				return 1;
			}
			else if (tempNode1.getData() < tempNode2.getData())
			{
				this.reverse();
				rOp.reverse();
				return -1;
			}
			else
			{
				this.reverse();
				rOp.reverse();
				return 0;
			}
		}			
	}
	
	public boolean equals(Object rightOp)
	{
		ReallyLongInt rOp = (ReallyLongInt) rightOp;
		if (compareTo(rOp) == 0)
			return true;
		else
			return false;
	}

	public void multTenToThe(int num)
	{
		this.reverse(); //flip
		for (int i = 0; i < num; i++) //add that shit
			this.add(0);
		this.reverse(); //flip back
	}

	public void divTenToThe(int num)
	{
		this.leftShift(num); //too easy
	}
}