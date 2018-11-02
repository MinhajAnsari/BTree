package b;
import java.util.Scanner;
public class B 
{
    public static void main(String[] args) 
    {
        System.out.print("Enter the Degree of the Tree :  ");
        Scanner obj=new Scanner(System.in);
        int tree_degree = obj.nextInt();;
        
        tree B_tree=new tree(tree_degree);
        for(int i=1;i<=30;i++)
            B_tree.insert(i);
        int choice=0;
        while(choice!=4)
        {
            for(int i =0;i<50;i++)
                System.out.println("");
            System.out.println("Press\n1) To Insert\n2) To Delete \n3) To Search\n4) Exit");
            choice=obj.nextInt();
            for(int i =0;i<50;i++)
                System.out.println("");
            if(choice==1)
            {
                System.out.print("Enter a Number To Insert : ");
                B_tree.insert(obj.nextInt());
            }
            if(choice==2)
            {
                System.out.print("Enter a Number To Delete : ");
                B_tree.remove(obj.nextInt());
            }
            if(choice==3)
            {
                System.out.print("Enter a Number : ");
                boolean search=B_tree.search(B_tree.root,obj.nextInt());
                if(search)
                    System.out.println("Number Found");
                else
                    System.out.println("Number Not Found");
            }
            if(choice!=3)
                for(int i =0;i<50;i++)
                    System.out.println("");
            System.out.println("\nB-Tree");
            B_tree.print();
            System.out.println("\n\nPress any digit to go Back to menu");
            obj.nextInt();
        }
        
    }
}
