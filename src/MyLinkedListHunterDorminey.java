// Class:		    Data Structures W01
// Term:		    Summer 2022
// Name:		    Hunter Dorminey
// Program Number:	Assignment 2 Part One
// IDE: 		    IntelliJ, JDK 18.0.1

public class MyLinkedListHunterDorminey <T> {
	Node head;
	Node tail;
	int size;

	public class Node{
		T data;
		Node link;
		Node(T element){
			data = element;
			link = null;
		}
	}
	
	MyLinkedListHunterDorminey(){
		head = null;
		tail = null;
		size = 0;
	}
	public T getFirst() throws RuntimeException{
		// checks if list is empty and throws exception if it is
		if(head == null) {
			throw new RuntimeException("in getFirst(): no elements in the list");
		}
		// returns object inside head if list is not empty
		else{
			return head.data;
		}
	}
	public T getLast() throws RuntimeException{
		// checks if list is empty and throws exception if it is
		if(head == null) {
			throw new RuntimeException("in getLast(): no elements in the list");
		}
		// returns object inside tail if list is not empty
		else {
			return tail.data;
		}
	}
	public void addLast(T newElement){
		// creates node containing the new object
		Node lastNode = new Node(newElement);
		// sets i equal to one since we know we want to insert the object pass the current tail
		int i = 1;
		Node last = head;
		// sets head and tail to the new object if the list is empty
		if(head == null){
			head = tail = lastNode;
		}
		// loops until reaching the current tail and then calls add to place the new object
		else{
			while(last.link != null){
				last = last.link;
				i++;
			}
			add(i, newElement);
		}
	}
	public void removeLast(){
		// throws exception if list is empty
		if(head == null) {
			throw new RuntimeException("in removeLast(): no elements in the list");
		}
		// deletes head and tail if there is only one object in list
		else if(head == tail){
			head = tail = null;
		}
		// loops until reaching the current tail before deleting it and setting the second to last item as the new tail
		else{
			Node last = head;
			while(last.link != tail){
				last = last.link;
			}
			last.link = null;
			tail = last;
		}
	}
	
	public int indexOf(T targetElement){
		Node checker = head;
		int i = 0;
		// loops until either reaching the tail or finds that the item is in the list
		while(checker != null && !checker.data.equals(targetElement)) {
			checker = checker.link;
			i++;
		}
		// returns the index is the item is in the list
		assert checker != null;
		if(checker.data.equals(targetElement)) {
			return i;
		}
		//returns negative one if the item is not in the list
		else {
			return -1;
		}
	}
	public Iterator iterator(){
		return new Iterator();
	}
	
	
	class Iterator {
		Node next; // to point [next node] object
		
		Iterator(){
			// next must be the first node of the list
                	next = head;
		}

		public T next(){
			 // return the data_field of [next node]
                	T data_field = next.data;
                	next = next.link;
                	return data_field;		
		}
		public boolean hasNext(){
			// return true when the [next node] exists
                	// return false when we don't have the [next node]
            return next != null;
        }
		
	}
	
	public void removeFirst() throws RuntimeException {
		if(head == null) {
			throw new RuntimeException("in removeFirst(): no elements in the list");
		}
		else if(head == tail) { // if(size==1)
			head = tail = null;
			size--;
		}
		else {
			head = head.link;
			size--;
		}
		
	}
	public void remove(int index) {
		if(index == 0) {
			removeFirst();
		}
		else if(head == tail) {
			head = tail = null;
			size--;
		} 
		else {
			Node cur = head;
			while (--index > 0) {
				cur = cur.link;
			}
			Node targetNode = cur.link;
			cur.link = targetNode.link;
			size--;
			if(cur.link == null)
				tail = cur;
		}
	}
	
	public void addFirst(T newElement){
		Node newNode = new Node(newElement);
		newNode.link = head;
		head = newNode;
		if(size==0) {
			tail = newNode;
		}
		size++;
	}
	
	public void add(int index, T newElement) {
		if(index == 0)
			addFirst(newElement);
		else {
			Node temp1 = head;
			while (--index > 0) {
				temp1 = temp1.link;
			}
			Node newNode = new Node(newElement);
			newNode.link = temp1.link;
			temp1.link = newNode;

			if (newNode.link == null) {
				tail = newNode;
			}
			size++;
		}
	}
	
	public String toString() {
		String str = "[";
		Node temp = head;
		
		while(temp != null) {
			str = str + temp.data;
			if(temp != tail) {
				str = str + ", ";
			}
			temp = temp.link;
		}		
		return str = str + "]";
	}
	
}
