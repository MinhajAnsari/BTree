package b;
public class tree 
{
    int degree;
    Node root;
    public tree(int s)
    {
        degree=s;
    }
    //Searching
    public boolean search(Node iter,int data)
    {
        int i=0;
        while(i<iter.n && data > iter.data[i])		
            i++;
	if(i<=iter.n && data == iter.data[i])
            return true;
        if(iter.isleaf)		
            return false;
        else	
            return search(iter.child[i],data);
    }
    //Insertion
    public void insert(int data)
    {
        if(root==null)
        {
            Node new_node=new Node(degree,true);   
            new_node.data[new_node.n]=data;
            root=new_node;
            new_node.n++;
        }
        else if(root.n==2*degree-1)
        {
            Node new_node=new Node(degree,false);
            new_node.child[0]=root;
            new_node.splitChild(0,root);
            int i=0;
            if(new_node.data[0]<data)
                i++;
            insert_in_leaf(new_node.child[i],data);
            root = new_node;
        }
        else
            insert_in_leaf(root,data);
    }
    public void insert_in_leaf(Node iter, int data)
    {
        int i = iter.n;
	if(iter.isleaf)
        {
            while(i >= 1 && data < iter.data[i-1])
            {
                iter.data[i] = iter.data[i-1];
                i--;
            }
            iter.data[i] = data;
            iter.n++; 
        }	
        else
        {
            int j = 0;
            while(j < iter.n  && data > iter.data[j])
                j++;
            if(iter.child[j].n==degree*2-1)
            {
                iter.splitChild(j, iter.child[j]);
                if(data > iter.data[j])
                        j++;
            }
            insert_in_leaf(iter.child[j],data);
        }
    }
    //Deletion
    public void remove(int data)
    {
        if (root==null)
        {
            System.out.println("The tree is empty\n");
            return;
        }
        root.remove(data);
        if (root.n==0)
        {
            Node tmp = root;
            if (root.isleaf)
                root = null;
            else
                root = root.child[0];
        }
        return;
    }
    public void print()
    {
        if(root!=null)
            root.print_node();
    }
}