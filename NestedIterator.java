import java.util.Iterator;
import java.util.List;
import java.util.Stack;

interface NestedInteger {
     public boolean isInteger();
     // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
     public Integer getInteger();
     // @return the nested list that this NestedInteger holds, if it holds a nested list
     // Return empty list if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
  }
  
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        this.st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty())
        {
            if(!st.peek().hasNext())
            {
                st.pop();
            }
            else if((nextEl = st.peek().next()).isInteger())
            {
                return true;
            }
            else
            {
                st.push(nextEl.getList().iterator());
            }
        }  
        return false;      
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */