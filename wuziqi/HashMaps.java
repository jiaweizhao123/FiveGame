package wuziqi;

import java.util.ArrayList;
public class HashMaps {
    static class HashNode<String,Integer>{   //内部类创建链表形式的哈希节点
        String key;
        Integer value;
        HashNode next;
        public HashNode(String key,Integer value){
            this.key=key;
            this.value=value;
        }
    }
    private ArrayList<HashNode<String,Integer>> bucketArray;  //创建寻址数组
    private int size;
    private int numBucket;
    public HashMaps(){
        bucketArray=new ArrayList<>();
        numBucket=10;
        size=0;
        for(int i=0;i<10;i++){
            bucketArray.add(null);
        }
    }
    private int getBucketIndex(String key){  //查找给定key在hash表中的下标
        int hashCode=key.hashCode();
        int index=hashCode%numBucket;
        return index;
    }
    public void add(String key,Integer value){  //哈希表增加元素函数
        int bucketIndex=getBucketIndex(key);
        HashNode<String,Integer> head=bucketArray.get(bucketIndex);
        while(head!=null){
            if(head.key.equals(key)){
                head.value=value;
            }
            head=head.next;
        }
       head=bucketArray.get(bucketIndex);
       HashNode<String,Integer> newNode=new HashNode<String,Integer>(key, value);
       newNode.next=head;
       bucketArray.set(bucketIndex, newNode);
       if((1.0*size)/numBucket>=0.7){ //当哈希表容量达到70%时自动扩大
           generatebiggerArray();
       }

    }
    public void generatebiggerArray(){ //哈希表扩容
        ArrayList<HashNode<String,Integer>> temp=bucketArray;
        bucketArray=new ArrayList<>();
        numBucket*=2;
        for(int i=0;i<numBucket;i++){
            bucketArray.add(null);
        }
        size=0;
        for(HashNode<String,Integer> headNode: temp){
            while(headNode!=null){
                add(headNode.key, headNode.value);
                headNode=headNode.next;
            }
        }
    }
    public Integer get(String key){  //通过key查找哈希表中对应值
        int bucketIndex=getBucketIndex(key);
        HashNode<String,Integer> headNode=bucketArray.get(bucketIndex);
        while(headNode!=null){
            if(headNode.key.equals(key)){
                return headNode.value;
            }
            headNode=headNode.next;
        }
        return null;
    }
    public Integer remove(String key){  //删除哈希表中对应key项操作
        int bucketIndex=getBucketIndex(key);
        HashNode<String,Integer> head=bucketArray.get(bucketIndex);
        HashNode<String,Integer> prev=null;
        while(head!=null){
            if(head.equals(key))
            break;
            prev=head;
            head=head.next;
        }
        if(head==null)
        return null;
        if(prev!=null){
            prev.next=head.next;
        }else{
            bucketArray.set(bucketIndex, head.next);
        }
        size--;
        return head.value;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void initmap(){
        this.add("01", 25);
		this.add("02", 22);
		this.add("001", 17);
        this.add("002", 12);
        this.add("021",10);
        this.add("012",13);
		this.add("0001", 17);
		this.add("0002", 12);
        this.add("00000", 1);
        this.add("0000", 1);
        this.add("000", 1);
        this.add("00", 1);
        this.add("0", 1);
        this.add("0200",22);
        this.add("0100", 27);
        this.add("0020", 20);
        this.add("0010", 23);
        this.add("020",22);
        this.add("010", 24);
		this.add("0102",25);
		this.add("0201",22);
		this.add("0012",15);
		this.add("0021",10);
		this.add("01002",25);
		this.add("02001",22);
		this.add("00102",17);
		this.add("00201",12);
		this.add("00012",15);
		this.add("00021",10);
        this.add("0001",20);
        this.add("0002",17);
        this.add("01001",75);
		this.add("01000",25);
		this.add("02000",22);
		this.add("00100",19);
		this.add("00200",14);
		this.add("00010",17);
		this.add("00020",12);
		this.add("00001",15);
        this.add("00002",10);
        this.add("00101",70);
        this.add("00202",67);
        this.add("010",25);
        this.add("0100",26);
		this.add("0010",13);
		this.add("0101",65);
		this.add("0202",60);
		this.add("0110",80);
		this.add("0220",76);
		this.add("011",100);
		this.add("022",86);
		this.add("0011",65);
        this.add("0022",60);
        this.add("02002", 50);
        this.add("01001", 53);
        
		this.add("01012",65);
		this.add("02021",60);
		this.add("01102",80);
		this.add("02201",76);
		this.add("01120",80);
		this.add("02210",76);
		this.add("00112",65);
        this.add("00221",60);
        this.add("0112", 80);
        this.add("0221",76);

		this.add("01100",80);
		this.add("02200",76);
		this.add("01010",75);
		this.add("02020",70);
		this.add("00110",75);
		this.add("00220",70);
		this.add("00011",75);
		this.add("00022",70);
		
		
		this.add("0111",180);
		this.add("0222",170);
        this.add("00222",125);
        this.add("00111",130);
		this.add("01112",150);
		this.add("02221",140);
		this.add("01110", 1100);
		this.add("02220", 1050);
		this.add("01101",1000);
		this.add("02202",800);
		this.add("01011",1000);
		this.add("02022",800);
		
		this.add("01111",3000);
		this.add("02222",3500);
    }
}