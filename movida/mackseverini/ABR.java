package movida.mackseverini;

public class ABR<T extends Comparable<T>> implements IABR<T>
{

  protected AbrNode<T> root;

  public ABR(){
    this.root = null;
  }

  public ABR(T key){
    this.root = new AbrNode(key);
  }

  public ABR(AbrNode<T> root){
    this.root = root;
  }

  public AbrNode<T> getRoot(){
    return this.root;
  }

  public void setRoot(AbrNode<T> root){
    this.root = root;
  }

  /*public void printAbr(AbrNode<T> rootNode)
  {
    if(rootNode == null)
    {
      System.out.println("Empty tree\n");
      return;
    }

    List<AbrNode<T>> thisLevel = new List<AbrNode<T>>(rootNode);
    while (thisLevel.getSize() > 0)
    {
      System.out.println();

      List<AbrNode<T>> nextLevel = new List<AbrNode<T>>();
      for(Node2<AbrNode<T>> iter = (Node2<AbrNode<T>>)thisLevel.getHead(); iter != null; iter = (Node2<AbrNode<T>>)iter.getNext())
      {
        System.out.print(" " + iter.getValue().getKey());
        if(iter.getValue().getLeftChild() != null)
          nextLevel.addTail(iter.getValue().getLeftChild());
        if(iter.getValue().getRightChild() != null)
          nextLevel.addTail(iter.getValue().getRightChild());
      }

      thisLevel = nextLevel;
    }
  }*/

  public class AbrNode<T extends Comparable<T>> implements IAbrNode<T>
  {
    protected T key;
    protected AbrNode<T> left;
    protected AbrNode<T> right;

    public AbrNode()
    {
        this.key = null;
        this.left = null;
        this.right = null;
    }

    // leaf
    public AbrNode(T key)
    {
      this.key = key;
      this.left = null;
      this.right = null;
    }

    // intermediate node
    public AbrNode(T key, AbrNode<T> left, AbrNode<T> right)
    {
      this.key = key;
      this.left = left;
      this.right = right;
    }

    public void setKey(T key)
    {
      this.key = key;
    }

    public T getKey()
    {
        return this.key;
    }

    public void setLeftChild(AbrNode<T> left)
    {
      this.left = left;
    }

    public AbrNode<T> getLeftChild()
    {
      return this.left;
    }

    public void setRightChild(AbrNode<T> right)
    {
      this.right = right;
    }

    public AbrNode<T> getRightChild()
    {
      return this.right;
    }

    /*@Override
    public T compareTo(AbrNode<T> nodeToBeCompared)
    {
      return this.compareTo(nodeToBeCompared);
    }*/
  }

  public boolean insert(T keyToInsert)
  {
    AbrNode<T> nodeToInsert = new AbrNode(keyToInsert);

    // empty tree
    if(this.getRoot() == null)
      this.setRoot(nodeToInsert);
    // searching for right position
    else
    {
      AbrNode<T> currentNode = this.getRoot();
      AbrNode<T> parent = null;
      while(currentNode != null)
      {
        parent = currentNode;

        if(keyToInsert.compareTo(currentNode.getKey()) <= 0)
          currentNode = currentNode.getLeftChild();
        else
          currentNode = currentNode.getRightChild();
      }

        if(keyToInsert.compareTo(parent.getKey()) <= 0)
        parent.setLeftChild(nodeToInsert);
      else
        parent.setRightChild(nodeToInsert);
    }

    return true;
  }

  public boolean delete(T keyToFind)
  {
      // root case
      if(keyToFind.compareTo(this.root.getKey()) == 0)
        return deleteRoot();
      else
      {
        AbrNode<T> nodeToFind = this.root;
        AbrNode<T> parent = null;
        while(nodeToFind != null && keyToFind.compareTo(nodeToFind.getKey()) != 0)
        {
          parent = nodeToFind;
          if(keyToFind.compareTo(nodeToFind.getKey()) < 0)
            nodeToFind = nodeToFind.getLeftChild();
          else
            nodeToFind = nodeToFind.getRightChild();
        }

        // leaf case
        if(nodeToFind.getLeftChild() == null && nodeToFind.getRightChild() == null)
        {
          if(parent.getLeftChild() == nodeToFind)
            parent.setLeftChild(null);
          else
            parent.setRightChild(null);
        }
        else
        // intermediate node case
          this.deleteIntermediateNode(nodeToFind, parent);
      }

      return true;
  }

  protected boolean deleteRoot()
  {
    // no child case
    if(this.root.getLeftChild() == null && this.root.getRightChild() == null)
      this.setRoot(null);
    // 2 children case
    else if(this.root.getLeftChild() != null && this.root.getRightChild() != null)
    {
      // choose predecessor as substitute
      AbrNode<T> substitute = this.root.getLeftChild();
      AbrNode<T> substituteParent = null;
      while(substitute.getRightChild() != null)
      {
        substituteParent = substitute;
        substitute = substitute.getRightChild();
      }

      // substitute the node to be cancelled with the predecessor
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
      this.setRoot(this.getRoot().getLeftChild());
    // 1 child case (right)
    else
      this.setRoot(this.getRoot().getRightChild());

      return true;
  }

  protected boolean deleteIntermediateNode(AbrNode<T> nodeToDelete, AbrNode<T> parent)
  {
    // 2 children case
    if(nodeToDelete.getLeftChild() != null && nodeToDelete.getRightChild() != null)
    {
      // search for the predecessor as substitute
      AbrNode<T> substitute = nodeToDelete.getLeftChild();
      AbrNode<T> substituteParent = null;
      while(substitute.getRightChild() != null)
      {
        substituteParent = substitute;
        substitute = substitute.getRightChild();
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
          this.delete(substitute.getKey());
          parent.setLeftChild(substitute);
          parent.getLeftChild().setRightChild(nodeToDelete.getRightChild());
          parent.getLeftChild().setLeftChild(nodeToDelete.getLeftChild());
        }
        else
        {
          this.delete(substitute.getKey());
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

  public boolean search(T keyToFind){
    boolean result = false;
    AbrNode<T> nodeChecked = this.root;

    // search for the node
    while((nodeChecked != null) && (keyToFind.compareTo(nodeChecked.getKey()) != 0))
    {
      if(keyToFind.compareTo(nodeChecked.getKey()) < 0)
        nodeChecked = nodeChecked.getLeftChild();
      else
        nodeChecked = nodeChecked.getRightChild();
    }

    if(nodeChecked != null)
      result = true;

    return result;
  }

  public boolean update(T update, T keyToFind){
    this.delete(keyToFind);
    this.insert(update);

    return true;
  }
}
