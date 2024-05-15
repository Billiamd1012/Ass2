package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.List;

public class DSEList implements List {
	
	public Node head;
	private Node tail;

	public DSEList() {
		this.head = null;
		this.tail = null;
	}

	public DSEList(Node head_) {
		this.head = head_;
		this.tail = head_;
		while (this.tail != null && this.tail.next != null) {
			this.tail = this.tail.next;
		}
	}

	// Takes a list then adds each element into a new list
	public DSEList(DSEList other) {
		this.head = null;
		this.tail = null;
		if (other.head != null) {
			Node currentNode = other.head;
			this.head = new Node(currentNode.next, currentNode.prev, currentNode.getString());
			Node newNode = this.head;
			while (currentNode.next != null) {
				currentNode = currentNode.next;
				newNode.next = new Node(currentNode.next, newNode, currentNode.getString());
				newNode = newNode.next;
			}
			this.tail = newNode;
		}
	}

	// Returns the index of the String parameter 
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
	
	// Returns String at parameter's index
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
		return null;
	}

	// Checks if there is a list
	public boolean isEmpty() {
		return this.head == null;
	}

	// Return the size of the list
	public int size() {
		int counter = 0;
		Node currentNode = this.head;
		while (currentNode != null) {
			counter++;
			currentNode = currentNode.next;
		}
		return counter;
	}
	
	// Take each element of the list and writes them to a string 
	@Override
	public String toString() {
		if (this.head == null) {
			return "";
		}
		StringBuilder listString = new StringBuilder();
		Node currentNode = this.head;
		while (currentNode != null) {
			listString.append(currentNode.getString());
			if (currentNode.next != null) {
				listString.append(" ");
			}
			currentNode = currentNode.next;
		}
		return listString.toString();
	}

	// Add the parameter String at the end of the list
	public boolean add(String obj) {
		Node newNode = new Node(null, this.tail, obj);
		if (this.head == null) {
			this.head = newNode;
		} else {
			this.tail.next = newNode;
		}
		this.tail = newNode;
		return true;
	}

	// Add String at parameter's index
	public boolean add(int index, String obj) {
		if (index < 0) return false;
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
		if (counter == index) {
			return add(obj);
		}
		return false;
	}

	// Searches list for parameter's String return true if found
	public boolean contains(String obj) {
		Node currentNode = this.head;
		while (currentNode != null) {
			if (currentNode.getString().equals(obj)) {
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}

	// Remove the String at the parameter's index
	public String remove(int index) {
		if (index < 0) return "Index not found";
		int counter = 0;
		Node currentNode = this.head;
		while (currentNode != null) {
			if (counter == index) {
				if (currentNode.prev != null) {
					currentNode.prev.next = currentNode.next;
				}
				if (currentNode.next != null) {
					currentNode.next.prev = currentNode.prev;
				}
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

	// Removes the parameter's String from the list
	public boolean remove(String obj) {
		int counter = 0;
		Node currentNode = this.head;
		while (currentNode != null) {
			if (currentNode.getString().equals(obj)) {
				remove(counter);
				return true;
			}
			currentNode = currentNode.next;
			counter++;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		Node currentNode = head;
		while (currentNode != null) {
			result = 31 * result + (currentNode.getString() == null ? 0 : currentNode.getString().hashCode());
			currentNode = currentNode.next;
		}
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || getClass() != other.getClass()) return false;
		DSEList dseList = (DSEList) other;
		Node thisNode = this.head;
		Node otherNode = dseList.head;
		while (thisNode != null && otherNode != null) {
			if (!thisNode.getString().equals(otherNode.getString())) {
				return false;
			}
			thisNode = thisNode.next;
			otherNode = otherNode.next;
		}
		return thisNode == null && otherNode == null;
	}
}
