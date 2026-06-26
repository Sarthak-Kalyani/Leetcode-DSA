class Node{
    int key,value,freq;
    Node prev,next;
    Node(int key,int value){
        this.key=key;
        this.value=value;
        this.freq=1;
    }
}
class DoublyLinkedList{
    Node head,tail;
    int size;
    DoublyLinkedList(){
        head=new Node(0,0);
        tail=new Node(0,0);
        head.next=tail;
        tail.prev=head;
    }
    void add(Node node){
        node.next=head.next;
        node.prev=head;
        head.next.prev=node;
        head.next=node;
        size++;
    }
    void remove(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
        size--;
    }
    Node removeLast(){
        if(size==0) return null;
        Node node=tail.prev;
        remove(node);
        return node;
    }
}
class LFUCache{
    int capacity,minFreq;
    HashMap<Integer,Node> cache;
    HashMap<Integer,DoublyLinkedList> freqMap;
    public LFUCache(int capacity){
        this.capacity=capacity;
        minFreq=0;
        cache=new HashMap<>();
        freqMap=new HashMap<>();
    }
    public int get(int key){
        if(!cache.containsKey(key)) return -1;
        Node node=cache.get(key);
        update(node);
        return node.value;
    }
    public void put(int key,int value){
        if(capacity==0) return;
        if(cache.containsKey(key)){
            Node node=cache.get(key);
            node.value=value;
            update(node);
            return;
        }
        if(cache.size()==capacity){
            DoublyLinkedList list=freqMap.get(minFreq);
            Node remove=list.removeLast();
            cache.remove(remove.key);
        }
        Node node=new Node(key,value);
        cache.put(key,node);
        minFreq=1;
        DoublyLinkedList list=freqMap.getOrDefault(1,new DoublyLinkedList());
        list.add(node);
        freqMap.put(1,list);
    }
    private void update(Node node){
        int f=node.freq;
        DoublyLinkedList list=freqMap.get(f);
        list.remove(node);
        if(f==minFreq && list.size==0){
            minFreq++;
        }
        node.freq++;
        DoublyLinkedList newList=freqMap.getOrDefault(node.freq,new DoublyLinkedList());
        newList.add(node);
        freqMap.put(node.freq,newList);
    }
}