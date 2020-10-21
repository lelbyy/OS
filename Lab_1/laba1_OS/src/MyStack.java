public class MyStack<T> {
    private class Node<T>{
        T data;
        private Node<T> next;
        Node(T data){
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
    private Node<T> root = null;

    public void push(T data){
        Node<T> cash = null;
        if(root == null){
            root = new Node<T>(data);
            return;
        }

        cash = root;
        root = new Node<T>(data);
        root.next = cash;
    }

    public T pop(){
        if(root == null){
            return null;
        }
        Node<T> cash = null;
        cash = root;
        root = root.next;
        return cash.data;
    }

    public int size(){
        int count = 0;
        Node<T> cash = root;
        while(cash != null){
            cash = cash.next;
            count++;
        }

        return count;
    }
    public void print(){
        while(root != null){
            System.out.println(root.data);
            Node<T> cash = null;
            cash = root;
            root = root.next;
        }
    }

    public void clear(){
        root = null;
    }
}
