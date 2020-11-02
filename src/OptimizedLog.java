import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;

public class OptimizedLog<Item> implements Collection<Item> {
    private Node first;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss.SSS");
     private int size=0;
    private class Node<Item> {
        private Item item;
        private Node next;
        private String first;
        private String last;
        private int count;

        public Node(Item item, int count) {
            this.item = item;
            this.count = count;
            first = formatter.format(LocalDateTime.now());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() { return first==null; }

    @Override
    public boolean contains(Object o) {
        Node<Item> current = first;
        while (current!= null){
            if(current.item.equals(o))
                return true;
            current=current.next;
        }
        return false;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    @Override
    public Object[] toArray() {
        Node current=first;
        Object object[]=new Object[size];
        int i=0;
        while (current!=null){
            object[i]=current.item;
            current=current.next;
            i++;
        }
        return object;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Node current=first;
        int i=0;
        while (current!=null){
            a[i]=(T)current.item;
            current=current.next;
            i++;
        }
        return a;
    }

    @Override
    public boolean add(Item item) {
        Node<Item>current = first;
        if (first == null) {
            first = new Node(item, 1);
            size++;
            return false;
        }
        while (current.next != null) {
            current = current.next;
        }
        if (current.item == item) {
            current.last = formatter.format(LocalDateTime.now());
            current.count++;
            return false;
        }
        current.next = new Node(item, 1);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object item:c){
           if(!contains(item)){
               return false;
           }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Item> c) {
        int Initialsize=this.size();
        for(Item item:c){
            add(item);
        }
        int ChangeSize=this.size();
        return ChangeSize>Initialsize;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
          first=null;
    }

    public String toString(){
         Node current=first;
         while(current!=null){
             if(current.last==null){
                 System.out.println( "[" + current.first+"]" + "[" + current.count+" times"+"]"+":"+current.item);
             }
             else {
                 System.out.println("[" + current.first + " - " + current.last + "]" + "[" + current.count +" times"+ "]" + ":" + current.item);
             }
             current=current.next;

         }
         return "";
    }

    private class LinkedIterator implements Iterator<Item> {
        Node<Item>current = OptimizedLog.this.first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {

            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
