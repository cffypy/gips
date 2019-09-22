import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
//        LinkedList<Integer> list = new LinkedList<Integer>();
//        list.add(0);
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        takeNum(list,3);
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        node1.value = 2;
        node2.value = 2;
        node3.value = 3;
        node4.value = 3;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(revertNode(node1));

    }

    static void takeNum(LinkedList<Integer> list,int internal){
        int index=0;
        int size = list.size();
        while(size>1){
            index += internal;
            if(index>size-1)index=index%(size);
            System.out.println(list.get(index));
            list.remove(index);
            size = list.size();
        }
        System.out.println(list.get(0));
    }

    static class Node{
        int value;
        Node next;

        @Override
        public String toString() {
            return "" + value+" "+next;

        }
    }
    private static Node deleteRepeatNode(Node list){
        Node pnew = list;
        while(pnew!=null){
            if(pnew.next!=null){
                if(pnew.value==pnew.next.value){
                    pnew.next = pnew.next.next;
                }
            }
            pnew = pnew.next;
        }
        return list;
    }

    static Node revertNode(Node list){

        Node pnew = new Node();
        Node p = pnew;
        while(list.next!=null){
            Node l = list.next;
            list.next = null;
            pnew.next = list;
            pnew = pnew.next;
            list = l;
        }
        return p.next;


    }
}
