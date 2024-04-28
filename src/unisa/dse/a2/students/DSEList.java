package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.List;

/**
 * @author simont
 *
 */
public class DSEList implements List {
	
	public Node head;
	private Node tail;

	public DSEList() {
		
	}
	public DSEList(Node head_) {
		head = head_;
	}
	
	//Takes a list then adds each element into a new list
	public DSEList(DSEList other) {
		this.head = null;
		this.tail = null;
		// check if other DSEList is not empty
		if (other.head != null) {
			Node currentNode = other.head;
			this.head = new Node(currentNode.next, currentNode.prev, currentNode.getString());
			while (currentNode.next != null) {
				currentNode = currentNode.next;
				this.head.next = new Node(currentNode.next, currentNode.prev, currentNode.getString());
			}
			this.tail = currentNode;
		}

	}

	//remove the String at the parameter's index
	public String remove(int index) {
		int counter = 0;
		Node currentNode = this.head;
		while (currentNode != null) {
			if (counter == index) {
				//remove the current node
				//set the previous node's next to the current node's next
				if (currentNode.prev != null) {
					currentNode.prev.next = currentNode.next;
				}
				//set the next node's previous to the current node's previous
				if (currentNode.next != null) {
					currentNode.next.prev = currentNode.prev;
				}
				//if the current node is the head or tail, set the head or tail to the next or previous node
				if (currentNode == this.head) {
					this.head = currentNode.next;
				}
				if (currentNode == this.tail) {
					this.tail = currentNode.prev;
				}
				return currentNode.getString();
			}
			currentNode = currentNode.next;
			counter++;
		}
		return "Index not found";
	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		int counter = 0;
		Node currentNode = this.head;
		while (currentNode != null) {
			if (currentNode.getString().equals(obj)) {
				return counter;
			}
			currentNode = currentNode.next;
			counter++;
		}
		return -1;
	}
	
	//returns String at parameter's index
	public String get(int index) {
		int counter = 0;
		Node currentNode = this.head;
		while (currentNode != null) {
			if (counter == index) {
				return currentNode.getString();
			}
			currentNode = currentNode.next;
			counter++;
		}
		return "Index not found";
	}

	//checks if there is a list
	public boolean isEmpty() {
		if (this.head == null) {
			return true;
		}
		return false;
	}

	//return the size of the list
	public int size() {
		int counter = 0;
		Node currentNode = this.head;
		while (currentNode != null) {
			counter++;
			currentNode = currentNode.next;
		}
		return counter;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		String listString = "";
		Node currentNode = this.head;
		while (currentNode.next != null) {
			listString += currentNode.getString() + " ";
			currentNode = currentNode.next;
		}
		listString += currentNode.getString();
		return listString;
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
		Node newNode = new Node(null, this.tail, obj);
		if (this.head == null) {
			this.head = newNode;
		}
		if (this.tail != null) {
			this.tail.next = newNode;
		}
		this.tail = newNode;
		return true;
	}

	//add String at parameter's index
	public boolean add(int index, String obj) {
		int counter = 0;
		Node currentNode = this.head;
		while (currentNode != null) {
			if (counter == index) {
				Node newNode = new Node(currentNode, currentNode.prev, obj);
				if (currentNode.prev != null) {
					currentNode.prev.next = newNode;
				}
				currentNode.prev = newNode;
				if (currentNode == this.head) {
					this.head = newNode;
				}
				return true;
			}
			currentNode = currentNode.next;
			counter++;
		}
		return false;

	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		return true;
	}
	
}
