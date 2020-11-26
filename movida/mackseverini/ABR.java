package movida.mackseverini;

import movida.mackseverini.IAbrNode;

public class ABR<T extends Comparable<T>> implements IABR<T>
{
  protected IAbrNode<T> root;

  public ABR() {this.root = null;}

  public ABR(T key) {this.root = new AbrNode(key);}

  public ABR(IAbrNode<T> root) {this.root = (AbrNode)root;}


  protected class AbrNode<T extends Comparable<T>> implements IAbrNode<T>, Comparable<AbrNode>
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

    @Override
    public void setKey(T key) {this.key = key;}

    @Override
    public T getKey() {return this.key;}

    @Override
    public void setLeftChild(IAbrNode<T> left) {this.left = (AbrNode)left;}

    @Override
    public IAbrNode<T> getLeftChild() {return this.left;}

    @Override
    public void setRightChild(IAbrNode<T> right) {this.right = (AbrNode)right;}

    @Override
    public IAbrNode<T> getRightChild() {return this.right;}
  }

  public boolean insert(T keyToInsert)
  {
    AbrNode<T> nodeToInsert = new AbrNode(keyToInsert);

    // empty tree
    if(this.root == null)
      this.root = nodeToInsert;
    // searching for right position
    else
    {
      AbrNode<T> currentNode = (AbrNode)this.root;
      AbrNode<T> parent = null;
      while(currentNode != null)
      {
        parent = currentNode;

        if(keyToInsert.compareTo(currentNode.getKey()) <= 0)
          currentNode = (AbrNode)currentNode.getLeftChild();
        else
          currentNode = (AbrNode)currentNode.getRightChild();
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
        AbrNode<T> nodeToFind = (AbrNode)this.root;
        AbrNode<T> parent = null;
        while(nodeToFind != null && keyToFind.compareTo(nodeToFind.getKey()) != 0)
        {
          parent = nodeToFind;
          if(keyToFind.compareTo(nodeToFind.getKey()) < 0)
            nodeToFind = (AbrNode)nodeToFind.getLeftChild();
          else
            nodeToFind = (AbrNode)nodeToFind.getRightChild();
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
      this.root = null;
    // 2 children case
    else if(this.root.getLeftChild() != null && this.root.getRightChild() != null)
    {
      // choose predecessor as substitute
      AbrNode<T> substitute = (AbrNode)this.root.getLeftChild();
      AbrNode<T> substituteParent = null;
      while(substitute.getRightChild() != null)
      {
        substituteParent = substitute;
        substitute = (AbrNode)substitute.getRightChild();
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
      this.root = this.root.getLeftChild();
    // 1 child case (right)
    else
      this.root = this.root.getRightChild();

      return true;
  }

  protected boolean deleteIntermediateNode(AbrNode<T> nodeToDelete, AbrNode<T> parent)
  {
    // 2 children case
    if(nodeToDelete.getLeftChild() != null && nodeToDelete.getRightChild() != null)
    {
      // search for the predecessor as substitute
      AbrNode<T> substitute = (AbrNode)nodeToDelete.getLeftChild();
      AbrNode<T> substituteParent = null;
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

  public boolean search(T keyToFind)
  {
    boolean result = false;
    AbrNode<T> nodeChecked = (AbrNode)this.root;

    // search for the node
    while((nodeChecked != null) && (keyToFind.compareTo(nodeChecked.getKey()) != 0))
    {
      if(keyToFind.compareTo(nodeChecked.getKey()) < 0)
        nodeChecked = (AbrNode)nodeChecked.getLeftChild();
      else
        nodeChecked = (AbrNode)nodeChecked.getRightChild();
    }

    if(nodeChecked != null)
      result = true;

    return result;
  }

  public boolean update(T update, T keyToFind)
  {
    this.delete(keyToFind);
    this.insert(update);

    return true;
  }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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


  protected int abrHeight(IAbrNode<T> node, int level)
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

   protected void levelOrderTraversal(IAbrNode<T> node, int level)
   {
      if(node == null) {}
      else if(level == 0) {System.out.println(((AbrNode)node).getKey());}
      else
      {
        levelOrderTraversal((AbrNode)node.getLeftChild(), level-1);
        levelOrderTraversal((AbrNode)node.getRightChild(), level-1);
      }
   }
}
