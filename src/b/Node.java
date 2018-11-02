package b;
public class Node 
{
   Node []child;
   int []data;
   int n,degree;
   boolean isleaf;
   Node(int t,boolean leaf)
   {
       degree=t;
       isleaf=leaf;
       data=new int[2*degree-1];
       child=new Node[2*degree];
       n=0;
   }
   //Insertion
   public void splitChild(int i, Node y)
    {
        Node z=new Node(y.degree, y.isleaf);
        z.n=degree-1;   //n=3  z[3]
        for (int j = 0; j < degree-1; j++)
            z.data[j] = y.data[j+degree];
        if (y.isleaf==false)
        {
            for (int j = 0; j < degree; j++)
                z.child[j] = y.child[j+degree];
        }
        y.n = degree - 1;
        for (int j = n; j >= i+1; j--)
            child[j+1] = child[j];
        child[i+1] = z;
        for (int j = n-1; j >= i; j--)
           data[j+1] = data[j];
        data[i] = y.data[degree-1];
        n++;
    }
    //Deletion
    public int findKey(int data1)
    {
        int idx=0;
        while (data1<n && data[idx] < data1)
        ++idx;
        return idx;
    }
    public void remove(int data1)
    {
        int idx = findKey(data1);
        if (idx < n && data[idx] == data1)
        {
        if (isleaf)
            removeFromLeaf(idx);
        else
            removeFromNonLeaf(idx);
        }
    else
    {   
        if (isleaf)
        {
            System.out.println("The Number "+ data1 +" does not exist in the tree\n");
            return;
        }
        boolean flag = ( (idx==n)? true : false );
        if (child[idx].n < degree)
            fill(idx);
        if (flag && idx > n)
            child[idx-1].remove(data1);
        else
            child[idx].remove(data1);
    }
    return;
    }
    public void fill(int idx)
    {
        if (idx!=0 && child[idx-1].n>=degree)
            borrowFromPrev(idx);
        else if (idx!=n && child[idx+1].n>=degree)
            borrowFromNext(idx);
        else
        {
            if (idx != n)
                merge(idx);
            else
                merge(idx-1);
        }
        return;
    }
    public void borrowFromPrev(int idx)
    {
        Node children=child[idx];
        Node sibling=child[idx-1];
        for (int i=children.n-1; i>=0; --i)
            children.data[i+1] = children.data[i];
        if (!children.isleaf)
        {
            for(int i=children.n; i>=0; --i)
                children.child[i+1] = children.child[i];
        }
        children.data[0] = data[idx-1];
        if (!isleaf)
        children.child[0]=sibling.child[sibling.n];
        data[idx-1] = sibling.data[sibling.n-1];
        children.n += 1;
        sibling.n -= 1;
        return;
    }
    public void borrowFromNext(int idx)
    {
        Node children=child[idx];
        Node sibling=child[idx+1];
        children.data[(children.n)] = data[idx];
        if (!(children.isleaf))
            children.child[(children.n)+1] = sibling.child[0];
        data[idx] = sibling.data[0];
        for (int i=1; i<sibling.n; ++i)
        sibling.data[i-1] = sibling.data[i];
        if (!sibling.isleaf)
        {
            for(int i=1; i<=sibling.n; ++i)
                sibling.child[i-1] = sibling.child[i];
        }
        children.n += 1;
        sibling.n -= 1;
        return;
    }
    public void removeFromLeaf(int idx)
    {
        for (int i=idx+1; i<n; ++i)
            data[i-1] = data[i];
        n--;
        return;
    }
    public void removeFromNonLeaf(int idx)
    {
        int k = data[idx];
        if (child[idx].n >= degree)
        {
            int pred = getPred(idx);
            data[idx] = pred;
            child[idx].remove(pred);
        }
        else if(child[idx+1].n >= degree)
        {
            int succ = getSucc(idx);
            data[idx] = succ;
            child[idx+1].remove(succ);
        }
        else
        {
            merge(idx);
            child[idx].remove(k);
        }
        return;
    }
    public void merge(int idx)
    {
        Node children = child[idx];
        Node sibling = child[idx+1];
        children.data[degree-1] = data[idx];
        for (int i=0; i<sibling.n; ++i)
            children.data[i+degree] = sibling.data[i];
        if (!children.isleaf)
        {
            for(int i=0; i<=sibling.n; ++i)
                children.child[i+degree] = sibling.child[i];
        }
        for (int i=idx+1; i<n; ++i)
            data[i-1] = data[i];
        for (int i=idx+2; i<=n; ++i)
            child[i-1] = child[i];
        children.n += sibling.n+1;
        n--;
    return;
    }
    public int getPred(int idx)
    {
        Node cur=child[idx];
        while (!cur.isleaf)
            cur = cur.child[cur.n];
        return cur.data[cur.n-1];
    }
    public int getSucc(int idx)
    {
        Node cur = child[idx+1];
        while (!cur.isleaf)
            cur = cur.child[0];
        return cur.data[0];
    }
    //Print
    public void print_node()
    {
        int i;
        System.out.println(" ");
        for (i=0;i<n;i++)
            System.out.print(data[i]+"  ");
        for (i=0;i<n;i++)
            if(!isleaf)
                child[i].print_node();
        if (isleaf == false)
          child[i].print_node();
    }
 }