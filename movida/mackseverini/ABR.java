package movida.mackseverini;

import movida.mackseverini.Array;
import movida.mackseverini.IAbrNode;
import movida.mackseverini.Stack;

public class ABR<E extends Comparable<E>, T extends Comparable<T>> implements IABR<E, T>
{
  protected IAbrNode<E, T> root;

  public ABR() {this.root = null;}

  public ABR(E key, T value) {this.root = new AbrNode(key, value);}

  public ABR(IAbrNode<E, T> root) {this.root = (AbrNode)root;}


  protected class AbrNode<E extends Comparable<E>, T extends Comparable<T>> implements IAbrNode<E, T>, Comparable<IAbrNode<E, T>>
  {
    protected E key;      // Movie or Person array index (so "global" key)
    protected T value;    // Movie or Person attribute (so "local" key)
    protected AbrNode<E, T> left;
    protected AbrNode<E, T> right;

    public AbrNode()
    {
        this.key = null;
        this.value = null;
        this.left = null;
        this.right = null;
    }

    // leaf
    public AbrNode(E key, T value)
    {
      this.key = key;
      this.value = value;
      this.left = null;
      this.right = null;
    }

    // intermediate node
    public AbrNode(E key, T value, AbrNode<E, T> left, AbrNode<E, T> right)
    {
      this.key = key;
      this.value = value;
      this.left = left;
      this.right = right;
    }

    @Override
    public void setKey(E key) {this.key = key;}

    @Override
    public E getKey() {return this.key;}

    @Override
    public void setValue(T value) {this.value = value;}

    @Override
    public T getValue() {return this.value;}

    @Override
    public void setLeftChild(IAbrNode<E, T> left) {this.left = (AbrNode)left;}

    @Override
    public IAbrNode<E, T> getLeftChild() {return this.left;}

    @Override
    public void setRightChild(IAbrNode<E, T> right) {this.right = (AbrNode)right;}

    @Override
    public IAbrNode<E, T> getRightChild() {return this.right;}

    @Override
    public int compareTo(IAbrNode<E, T> nodeToBeCompared) {return this.key.compareTo(nodeToBeCompared.getKey());}
  }

  @Override
  public void setRoot(IAbrNode<E,T> root) {this.root = root;}

  @Override
  public IAbrNode<E, T> getRoot() {return this.root;}

  @Override
  public Integer getSize() {return this.getSizeRec(this.root);}

  protected Integer getSizeRec(IAbrNode<E, T> node)
  {
    if(node == null)
      return 0;
    else
      return 1 + this.getSizeRec(node.getLeftChild()) + this.getSizeRec(node.getRightChild());
  }

  @Override
  public Array<E> getNumMax(Integer num)
  {
    if(this.root == null)
      return null;

    Stack<IAbrNode<E, T>> movies = new Stack<IAbrNode<E, T>>();
    Array<E> maxIndexes = new Array<E>(num);
    AbrNode<E, T> node = (AbrNode<E,T>)this.root;
    int i = 0;

    movies.push(node);

    while(!movies.isEmpty() && i < num)
    {
      while(node.getRightChild() != null)
      {
        node = (AbrNode<E,T>)node.getRightChild();
        movies.push(node);
      }

      node = (AbrNode<E,T>)movies.pop();
      maxIndexes.set(i, node.getKey());
      i++;

      while(node.getLeftChild() == null && !movies.isEmpty() && i < num)
      {
        node = (AbrNode<E,T>)movies.pop();
        maxIndexes.set(i, node.getKey());
        i++;
      }

      if (node.getLeftChild() != null)
      {
        node = (AbrNode<E,T>)node.getLeftChild();
        movies.push(node);
      }
    }

    // System.out.println("\n\n\n\n");
    // for(int j = 0; j < maxIndexes.length; j++)
    //   System.out.println(maxIndexes.get(j));
    // System.out.println("\n\n\n\n");

    return maxIndexes;
  }

  @Override
  public boolean insert(T valueToInsert) {return false;}

  @Override
  public boolean insert(E keyToInsert, T valueToInsert)
  {
    if((keyToInsert != null) && (valueToInsert != null))
    {
      AbrNode<E, T> nodeToInsert = new AbrNode(keyToInsert, valueToInsert);

      // empty tree
      if(this.root == null)
        this.root = nodeToInsert;
      // searching for right position
      else
      {
        AbrNode<E, T> currentNode = (AbrNode)this.root;
        AbrNode<E, T> parent = null;
        while(currentNode != null)
        {
          parent = currentNode;

          if(valueToInsert.compareTo(currentNode.getValue()) <= 0)
            currentNode = (AbrNode)currentNode.getLeftChild();
          else
            currentNode = (AbrNode)currentNode.getRightChild();
        }

        if(valueToInsert.compareTo(parent.getValue()) <= 0)
          parent.setLeftChild(nodeToInsert);
        else
          parent.setRightChild(nodeToInsert);
      }

      return true;
    }
    else
      return false;
  }

  @Override
  public boolean deleteByKey(E keyToDelete) {return this.delete(searchByKeyRecursive(keyToDelete, this.root).getValue());}

  @Override
  public boolean delete(T valueToDelete)
  {
      if((this.root == null) || (valueToDelete == null))
      {
        System.out.println("NOTHING TO DELETE");
        return false;
      }
      // ROOT case
      if(valueToDelete.compareTo(this.root.getValue()) == 0)
        return deleteRoot();
      else
      {
        AbrNode<E, T> nodeToFind = (AbrNode)this.root;
        AbrNode<E, T> parent = null;
        // search for the node to be deleted
        while(nodeToFind != null && valueToDelete.compareTo(nodeToFind.getValue()) != 0)
        {
          parent = nodeToFind;
          if(valueToDelete.compareTo(nodeToFind.getValue()) < 0)
            nodeToFind = (AbrNode)nodeToFind.getLeftChild();
          else
            nodeToFind = (AbrNode)nodeToFind.getRightChild();
        }
        // if the node to be deleted isn't in the tree
        if(nodeToFind == null)
          return false;
        // LEAF case
        else if(nodeToFind.getLeftChild() == null && nodeToFind.getRightChild() == null)
        {
          if(parent.getLeftChild() == nodeToFind)
            parent.setLeftChild(null);
          else
            parent.setRightChild(null);

          return true;
        }
        // INTERMEDIATE NODE case
        else
          return this.deleteIntermediateNode(nodeToFind, parent);
      }
  }

  public boolean deleteByKey(T valueToDelete, E keyToDelete)
  {
      if((this.root == null) || (valueToDelete == null))
      {
        System.out.println("NOTHING TO DELETE");
        return false;
      }
      // ROOT case
      if(valueToDelete.compareTo(this.root.getValue()) == 0 && keyToDelete.compareTo(this.root.getKey()) == 0)
        return deleteRoot();
      else
      {
        AbrNode<E, T> nodeToFind = (AbrNode<E,T>)this.root;
        AbrNode<E, T> parent = null;
        // search for the node to be deleted
        while(nodeToFind != null && keyToDelete.compareTo(nodeToFind.getKey()) != 0)
        {
          parent = nodeToFind;
          if(valueToDelete.compareTo(nodeToFind.getValue()) <= 0)
            nodeToFind = (AbrNode<E,T>)nodeToFind.getLeftChild();
          else
            nodeToFind = (AbrNode<E,T>)nodeToFind.getRightChild();
        }

        // if the node to be deleted isn't in the tree
        if(nodeToFind == null)
          return false;
        // LEAF case
        else if(nodeToFind.getLeftChild() == null && nodeToFind.getRightChild() == null)
        {
          if(parent.getLeftChild() == nodeToFind)
            parent.setLeftChild(null);
          else
            parent.setRightChild(null);

          return true;
        }
        // INTERMEDIATE NODE case
        else
          return this.deleteIntermediateNode(nodeToFind, parent);
      }
  }

  protected boolean deleteRoot()
  {
    // no child case
    if(this.root.getLeftChild() == null && this.root.getRightChild() == null)
      this.root = null;
    // 2 children case
    else if(this.root.getLeftChild() != null && this.root.getRightChild() != null)
    {
      // choose predecessor as substitute
      AbrNode<E, T> substitute = (AbrNode)this.root.getLeftChild();
      AbrNode<E, T> substituteParent = null;
      while(substitute.getRightChild() != null)
      {
        substituteParent = substitute;
        substitute = (AbrNode)substitute.getRightChild();
      }

      // substitute the node to be cancelled with the predecessor
      this.root.setValue(substitute.getValue());
      this.root.setKey(substitute.getKey());
      // if the predecessor wasn't just the left child (maximum of left subtree)
      if(substituteParent != null)
      {
        if(substitute.getLeftChild() == null)
          substituteParent.setRightChild(null);
        else
          substituteParent.setRightChild(substitute.getLeftChild());
      }
      // if the predecessor was just the left child
      else
        this.root.setLeftChild(this.root.getLeftChild().getLeftChild());

    }
    // 1 child case (left)
    else if(this.root.getLeftChild() != null)
      this.root = this.root.getLeftChild();
    // 1 child case (right)
    else
      this.root = this.root.getRightChild();

      return true;
  }

  protected boolean deleteIntermediateNode(IAbrNode<E, T> nodeToDelete, IAbrNode<E, T> parent)
  {
    if(nodeToDelete == null || parent == null)
      return false;
    // 2 children case
    if(nodeToDelete.getLeftChild() != null && nodeToDelete.getRightChild() != null)
    {
      // search for the predecessor as substitute
      AbrNode<E, T> substitute = (AbrNode)nodeToDelete.getLeftChild();
      AbrNode<E, T> substituteParent = null;
      while(substitute.getRightChild() != null)
      {
        substituteParent = substitute;
        substitute = (AbrNode)substitute.getRightChild();
      }

      // check if the predecessor is the left child of nodeToDelete
      if(substituteParent == null)
      {
        // check if the nodeToDelete is a left or right child
        if(nodeToDelete == parent.getLeftChild())
        {
          parent.setLeftChild(substitute);
          parent.getLeftChild().setRightChild(nodeToDelete.getRightChild());
        }
        else
        {
          parent.setRightChild(substitute);
          parent.getRightChild().setRightChild(nodeToDelete.getRightChild());
        }
      }
      // the predecessor is the max of nodeToDelete left subtree
      else
      {
        // check if the nodeToDelete is a left or right child
        if(nodeToDelete == parent.getLeftChild())
        {
          this.delete(substitute.getValue());
          parent.setLeftChild(substitute);
          parent.getLeftChild().setRightChild(nodeToDelete.getRightChild());
          parent.getLeftChild().setLeftChild(nodeToDelete.getLeftChild());
        }
        else
        {
          this.delete(substitute.getValue());
          parent.setRightChild(substitute);
          parent.getRightChild().setRightChild(nodeToDelete.getRightChild());
          parent.getRightChild().setLeftChild(nodeToDelete.getLeftChild());
        }
      }
    }
    // only left child case
    else if(nodeToDelete.getLeftChild() != null && nodeToDelete.getRightChild() == null)
    {// SI POTREBBE TOGLIERE IL PRIMO CONTROLLO E LASCIARE SOLO IL SECONDO
      if(nodeToDelete == parent.getLeftChild())
        parent.setLeftChild(nodeToDelete.getLeftChild());
      else
        parent.setRightChild(nodeToDelete.getLeftChild());
    }
    // only right child case
    else
    {
      if(nodeToDelete == parent.getLeftChild())
        parent.setLeftChild(nodeToDelete.getRightChild());
      else
        parent.setRightChild(nodeToDelete.getRightChild());
    }

    return true;
  }

  @Override
  public Array<E> getAll(T valueToFind)
  {
    if(valueToFind == null)
      return null;

    Array<E> occurranceArray = new Array<E>(this.getSize());
    AbrNode<E, T> occurrance = (AbrNode<E, T>)this.get(valueToFind);
    ABR<E, T> currentSubTree = new ABR<E, T>();
    int i = 0;

    while(occurrance != null)
    {
      currentSubTree.setRoot(occurrance.getLeftChild());
      occurranceArray.set(i, occurrance.getKey());
      i++;
      occurrance = (AbrNode<E, T>)currentSubTree.get(valueToFind);
    }

    return occurranceArray;
  }

  @Override
  public boolean searchByKey(E keyToFind)
  {
    if(searchByKeyRecursive(keyToFind, this.root) != null && searchByKeyRecursive(keyToFind, this.root).getKey().compareTo(keyToFind) == 0)
      return true;
    else
      return false;
  }

  protected IAbrNode<E, T> searchByKeyRecursive(E keyToFind, IAbrNode<E, T> nodeChecked)
  {
    AbrNode<E, T> leftRecursiveCall = new AbrNode<E, T>();
    if(nodeChecked == null)
      return null;
    else if(nodeChecked.getKey().compareTo(keyToFind) == 0)
      return nodeChecked;
    else
    {
      leftRecursiveCall = (AbrNode<E, T>)searchByKeyRecursive(keyToFind, nodeChecked.getLeftChild());
      if(leftRecursiveCall != null)
        return leftRecursiveCall;
      else
        return searchByKeyRecursive(keyToFind, nodeChecked.getRightChild());
    }
  }

  public IAbrNode<E, T> get(T valueToFind)
  {
    if(valueToFind == null)
      return null;

    AbrNode<E, T> nodeChecked = (AbrNode)this.root;

    // search for the node
    while((nodeChecked != null) && (valueToFind.compareTo(nodeChecked.getValue()) != 0))
    {
      if(valueToFind.compareTo(nodeChecked.getValue()) < 0)
        nodeChecked = (AbrNode)nodeChecked.getLeftChild();
      else
        nodeChecked = (AbrNode)nodeChecked.getRightChild();
    }

    return nodeChecked;
  }

  @Override
  // says if valueToFind is in the tree
  public boolean search(T valueToFind)
  {
    if(this.get(valueToFind) != null && ((((AbrNode)this.get(valueToFind)).value).compareTo(valueToFind) == 0))
      return true;
    else
      return false;
  }

  protected E getIndex(T valueToFind)
  {
    if(valueToFind == null)
      return null;

    AbrNode<E, T> nodeChecked = (AbrNode<E,T>)this.root;

    // search for the node
    while((nodeChecked != null) && (valueToFind.compareTo(nodeChecked.getValue()) != 0))
    {
      if(valueToFind.compareTo(nodeChecked.getValue()) < 0)
        nodeChecked = (AbrNode<E,T>)nodeChecked.getLeftChild();
      else
        nodeChecked = (AbrNode<E,T>)nodeChecked.getRightChild();
    }

    if(nodeChecked != null && (nodeChecked.getValue()).compareTo(valueToFind) == 0)
      return nodeChecked.getKey();
    else
      return null;
  }

  @Override
  public boolean update(E keyToUpdate, T valueToUpdate, T valueToFind)
  {
    if(this.delete(valueToFind))
      return this.insert(keyToUpdate, valueToUpdate);
    else
      return false;
  }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//                                              USEFUL ONLY FOR TESTING                                           //
  public void printAbr()
  {
    if(this.root == null) {System.out.println("EMPTY TREE");}
    else
    {
      int height = this.abrHeight(this.root, 0);
      for(int i = 0; i <= height; i++)
        levelOrderTraversal(root, i);
    }
  }


  protected int abrHeight(IAbrNode<E, T> node, int level)
  {
      if(node == null) {return level;}
      else if(node.getLeftChild() != null && node.getRightChild() != null)
       return max(abrHeight(node.getLeftChild(), level + 1), abrHeight(node.getRightChild(), level + 1));
      else if(node.getLeftChild() == null && node.getRightChild() != null)
        return abrHeight(node.getRightChild(), level + 1);
      else if(node.getLeftChild() != null && node.getRightChild() == null)
        return abrHeight(node.getLeftChild(), level + 1);
      else
        return level;
   }

  protected int max(int value1, int value2)
  {
    if(value1 - value2 <= 0)
      return value2;
    else
      return value1;
  }

   protected void levelOrderTraversal(IAbrNode<E, T> node, int level)
   {
      if(node == null) {}
      else if(level == 0) {System.out.println("Key: " + ((AbrNode)node).getKey() + " Value: " + ((AbrNode)node).getValue());}
      else
      {
        levelOrderTraversal((AbrNode)node.getLeftChild(), level-1);
        levelOrderTraversal((AbrNode)node.getRightChild(), level-1);
      }
   }
}
