package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric<T> implements ListGeneric<T> {
	
	public NodeGeneric head;
	private NodeGeneric tail;

	public DSEListGeneric() {
		
	}
	public DSEListGeneric(NodeGeneric head_) {
		this.head = head_;
	}
	
	//Takes a list then adds each element into a new list
	public DSEListGeneric(DSEList other) { 
		this.head = null;
		this.tail = null;
		// check if other DSEList is not empty
		if (other.head != null) {
			// use the add method to add to the current list
			for (int i=0;i<other.size();i++){
				this.add(other.get(i));
			}
		}
	}

	//remove and return the item at the parameter's index
	@Override
	public T remove(int index) {
		int counter = 0;
		NodeGeneric currentNode = this.head;
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
				return (T) currentNode.get();
			}
			currentNode = currentNode.next;
			counter++;
		}
		return null;
	}

	//returns the index of the String parameter
	@Override 
	public int indexOf(T obj) {
		int counter = 0;
		NodeGeneric currentNode = this.head;
		while (currentNode != null) {
			if (currentNode.get().equals(obj)) {
				return counter;
			}
			currentNode = currentNode.next;
			counter++;
		}
		return -1;
	}
	
	//returns item at parameter's index
	@Override
	public T get(int index) {
		int counter = 0;
		NodeGeneric currentNode = this.head;
		while (currentNode != null) {
			if (counter == index) {
				return (T) currentNode.get();
			}
			currentNode = currentNode.next;
			counter++;
		}
		return null;
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
		NodeGeneric currentNode = this.head;
		while (currentNode != null) {
			currentNode = currentNode.next;
			counter++;
		}
		return counter;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		String listString = "";
		NodeGeneric currentNode = this.head;
		while (currentNode != null) {
			listString += currentNode.get().toString();
			currentNode = currentNode.next;
		}
		return listString;
	}

	//add the parameter item at of the end of the list
	public boolean add(Object obj) {
		NodeGeneric newNode = new NodeGeneric(null, this.tail, obj);
		if (this.head == null) {
			this.head = newNode;
		}
		if (this.tail != null) {
			this.tail.next = newNode;
		}
		this.tail = newNode;
		return true;
	}

	//add item at parameter's index
	public boolean add(int index, Object obj) {
		int counter = 0;
		NodeGeneric currentNode = this.head;
		while (currentNode != null) {
			if (counter == index) {
				NodeGeneric newNode = new NodeGeneric(currentNode, currentNode.prev, obj);
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
	public boolean contains(Object obj) {
		NodeGeneric currentNode = this.head;
		while (currentNode != null) {
			if (currentNode.get().equals(obj)) {
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}

	//removes the parameter's item form the list
	public boolean remove(Object obj) {
		int counter = 0;
		NodeGeneric currentNode = this.head;
		while (currentNode != null) {
			if (currentNode.equals(obj)) {
				remove(counter);
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		NodeGeneric thisNode = this.head;
		if (!(other instanceof DSEListGeneric)) {
			return false;
		}
		NodeGeneric otherNode = ((DSEListGeneric) other).head;
		while (thisNode != null && otherNode != null) {
			if (thisNode.get() != otherNode.get()) {
				return false;
			}
			thisNode = thisNode.next;
			otherNode = otherNode.next;
		}
		if (thisNode != null || otherNode != null) {
			return false;
		}
		return true;
	}
	
}
