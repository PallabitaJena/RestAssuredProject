package testPackage;

import java.util.ArrayList;

import org.testng.annotations.Test;


public class Arraylist1 {
	
	@Test
	public void method1()
	{
	ArrayList<String> arlList=new ArrayList<String>();
	System.out.println("size of arraylist:-->"+arlList.size());
	
	  arlList.add("D");
	  arlList.add("D");
	  arlList.add("U");
	  arlList.add("K");
	  arlList.add("E");
	  arlList.add("F");
	  //Recheck the size after adding elements
	  System.out.println("Size of ArrayList after adding elements: " + arlList.size());
	  

	  //Display all contents of ArrayList
	  System.out.println("List of all elements: " + arlList);
	  
	  //Remove some elements from the list
	  arlList.remove("D");
	  System.out.println("See contents after removing one element: " + arlList);
	  
	  //Remove element by index
	  arlList.remove(2);
	  System.out.println("See contents after removing element by index: " + arlList);
	  //Check size after removing elements
	  System.out.println("Size of arrayList after removing elements: " + arlList.size());
	  System.out.println("List of all elements after removing elements: " + arlList);
	  //Check if the list contains "K"
	  System.out.println(arlList.contains("K"));
	
	}
	
	

}
