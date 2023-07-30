
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deepanshu Sahu
 */
public class Sample {
    public static void main(String [] args)
    {
        int n,k;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
        int i,currIndex=0;
        ArrayList list = new ArrayList();
        for(i=1;i<=n;i++)       
            list.add(i);
        while(list.size()>1)
        {
            
            currIndex = (currIndex+k-1)%list.size();
            list.remove(currIndex);
        }
        System.out.println(list.get(0));
    }
}
