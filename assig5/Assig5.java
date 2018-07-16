// Michael Adams
// TuTh 1pm

import java.lang.Math;
import java.io.*;
import java.util.*;

public class Assig5
{
	public int numLeaf = 0;
	public int constant = 65;
	public BinaryNode<Character> rootNode = new BinaryNode<Character>();
	public BinaryNode<Character> tempNode = new BinaryNode<Character>();
	public BinaryNode<Character> currNode = new BinaryNode<Character>();
	public Scanner inF;	
	public StringBuilder sb = new StringBuilder();
	public char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	//MAIN
	public static void main(String[] args)
	{
		new Assig5(); //call constructor
	}
	
	//CONSTRUCTOR
	public Assig5()
	{
		//get input file name
		Scanner inScan = new Scanner(System.in);
		PrintWriter fileOut;
		while (true)
		{
			try
			{
				System.out.println("Enter filename containing Huffman tree info: ");
				inF = new Scanner(new File(inScan.nextLine()));
				break;
			}
			catch (IOException e)
			{
				System.out.println("Didn't work, try again");
			}
		}
		
		//build tree, print to console
		rootNode = buildTree(tempNode);
		
		//build table
		String[] codeArr = new String[numLeaf];
		codeArr = buildTable(rootNode, sb, codeArr);
		
		//interact
		boolean done = false;
		boolean valid = false;
		int choice, currInt;
		String str;
		while (!done)
		{
			System.out.println("Choose from the following: ");
			System.out.println("1) Encode a text string");
			System.out.println("2) Decode a text string");
			System.out.println("3) Quit");
			
			choice = inScan.nextInt();
			if (choice == 1) //encode string
			{
				System.out.println("Enter a String from the following characters: ");
				for (int i = 0; i < numLeaf; i++)
				{
					System.out.print(alphabet[i] + " ");
				}
				System.out.println();
				do //error checking 
				{
					str = inScan.next();
					for (int i = 0; i < str.length(); i++)
					{
						if ((int) str.charAt(i) < 0 || (int) str.charAt(i) > numLeaf + 65)
							valid = false;
						else 
							valid = true;
					}
					if (!valid)
						System.out.println("Your text string was bad (or sick), try again: ");
				}
				while (!valid);
				sb = new StringBuilder();
				for (int i = 0; i < str.length(); i++)
				{
					sb.append(codeArr[(int) str.charAt(i) - constant]);
					sb.append("\n");
				}
				System.out.println("Huffman String: ");
				System.out.println(sb);
			}
			else if (choice == 2) //decode string
			{
				System.out.println("Here is the encoding table: ");
				for (int i = 0; i < numLeaf; i++)
				{
					System.out.println(alphabet[i] + ": " + codeArr[i]);
				}
				System.out.println("Please enter a Huffman string (1 line, 0 spaces)");
				
				do //error checking loop
				{
					str = inScan.next();
					sb = new StringBuilder();
					tempNode = new BinaryNode<Character>();
					currNode = new BinaryNode<Character>();
					currNode = rootNode;
					for (int i = 0; i < str.length(); i++)
					{
						if (str.charAt(i) == '0' && currNode.getData().equals('\0'))
						{
							//System.out.println("if 0: curNode.getData = " + currNode.getData());
							tempNode = currNode.getLeftChild();
							currNode = tempNode;
						}
						else if (str.charAt(i) == '1' && currNode.getData().equals('\0'))
						{
							//System.out.println("if 1: curNode.getData = " + currNode.getData());
							tempNode = currNode.getRightChild();
							currNode = tempNode;
						}
						else 
						{
							//System.out.println("else: curNode.getData = " + currNode.getData());
							sb.append(currNode.getData());
							currNode = new BinaryNode<Character>();
							currNode = rootNode;
							i--;
						}
					}
					sb.append(currNode.getData());
				
					if (currNode.getData() == '\0') //error checking
					{
						//System.out.println("here 3");
						valid = false;
						System.out.println("Your Huffman code was sad! try again: ");
					}
					else
						valid = true;
				} while (!valid); 
				
				System.out.println("Text String: ");
				System.out.println(sb);
				System.out.println();
			}
			else //quit
			{
				done = true;
				System.out.println("see ya");
			}
		}
	}
	//recursively build binary tree
	public BinaryNode<Character> buildTree(BinaryNode T)
	{
		BinaryNode<Character> iNode = new BinaryNode<Character>();
		BinaryNode<Character> lNode = new BinaryNode<Character>();
		BinaryNode<Character> leftNode = new BinaryNode<Character>();
		BinaryNode<Character> rightNode = new BinaryNode<Character>();
		BinaryNode<Character> retNode = new BinaryNode<Character>();
		
		if (inF.next().equals("I")) //interior node
		{			
			iNode.setData('\0');
			leftNode = buildTree(iNode);
			iNode.setLeftChild(leftNode);
			rightNode = buildTree(iNode);
			iNode.setRightChild(rightNode);
			retNode = iNode;
		}
		else //if (inF.next().equals("L"))    leaf node
		{
			lNode.setData(inF.next().charAt(0));
			retNode = lNode;
			numLeaf++;
		}
		return retNode;
	}
	//build table
	public String[] buildTable(BinaryNode<Character> node, StringBuilder sb, String[] codeArr)
	{
		if (node != null)
		{
			sb.append(0);
			buildTable(node.getLeftChild(), sb, codeArr);
			sb.setLength(sb.length() - 1);
			//if (!node.getData().equals('\0'))
			//	System.out.print(node.getData() + " ");
			sb.append(1);
			buildTable(node.getRightChild(), sb, codeArr);
			sb.setLength(sb.length() - 1);
			
			int tempInt;
			char tempChar;
			if (!node.getData().equals('\0')) //append code if leaf node
			{
				tempChar = node.getData();
				tempInt = (int) tempChar;
				codeArr[tempInt - constant] = sb.toString();
			}
		}
		return codeArr;
	}
}

