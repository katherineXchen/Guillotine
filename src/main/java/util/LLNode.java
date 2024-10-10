package util;

/**
 * The node of a linked list
 * @author Katherine Chen
 */
public class LLNode<T> {
    /** The element stored in the node */
    private T element;

    /** A reference to the next node of the list */
    private LLNode<T> next;

    /**
     * The constructor
     * @param element  the element to store in the node
     * @param next  a reference to the next node of the list
     */
    public LLNode(T element, LLNode<T> next) {
        this.element = element;
        this.next = next;
    }

    /**
     * Returns the element stored in the node
     * @return the element stored in the node
     */
    public T getElement() {
        return element;
    }

    /**
     * Returns the next node of the list
     * @return the next node of the list
     */
    public LLNode<T> getNext() {
        return next;
    }

    /**
     * Changes the element stored in this node
     * @param element the new element that is replacing the old one
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Changes the node that comes after this node in the list
     * @param next the node that should come after this node in the list. It can be null.
     */
    public void setNext(LLNode<T> next) {
        this.next = next;
    }

    /**
     * Move head node to n places after
     * @param n places to move head to
     * @return new head node
     */
    public LLNode<T> moveBack(int n) {
        if (getNext() == null || n <= 0) {
            return this;
        }
        //make the next element of the current head node the new head
        LLNode<T> newHead = getNext();
        LLNode<T> curr = this;

        // move current head node to n places after
        int cnt = 0;
        while (cnt < n && curr != null) {
            curr = curr.getNext();
            cnt++;
        }

        if (curr == null || curr.getNext() == null) {
            return this;
        }
        //make the next element the element after the new current head node
        LLNode<T> next = curr.getNext();
        curr.setNext(this);
        setNext(next);

        return newHead;
    }

    /**
     * Move last node to first node
     * @return new head node
     */
    public LLNode<T> moveLastToFirst() {
        LLNode<T> head = this;
        if (head == null || head.getNext() == null) {
            return head;
        }
        //start from making the second to last element null
        LLNode<T> secLast = null;
        //start from assuming the last element is the head
        LLNode<T> last = head;

        // find the last node
        while (last.getNext() != null) {
            secLast = last;
            last = last.getNext();
        }

        secLast.setNext(null);
        last.setNext(head);

        head = last;

        return head;
    }

    /**
     * Move first node to last node
     * @return new head node
     */
    public LLNode<T> moveFirstToLast() {
        LLNode<T> head = this;
        if (head == null || head.getNext() == null) {
            return head;
        }
        //start from making the first element the head
        LLNode<T> first = head;
        //start from assuming the last element is the head
        LLNode<T> last = head;

        // find the last node
        while (last.getNext() != null) {
            last = last.getNext();
        }

        head = first.getNext();

        first.setNext(null);
        last.setNext(first);

        return head;
    }

    /**
     * Reverse the list
     * @return new head node
     */
    public LLNode<T> reverseList() {
        LLNode<T> head = this;
        LLNode<T> prev = null;
        LLNode<T> current = head;
        LLNode<T> next;
        // reverse one by one
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        head = prev;

        return head;
    }

    /**
     * Reverse first k nodes in the list
     * @param k k nodes
     * @return new head node
     */
    public LLNode<T> reverseFirstK(int k) {
        if (k <= 0) {
            return this;
        }

        LLNode<T> head = this;
        LLNode<T> temp = head;
        int count = 1;

        // find end of k nodes
        while (count < k) {
            temp = temp.getNext();
            count++;
        }

        LLNode<T> jointPoint = temp.getNext();
        temp.setNext(null);

        LLNode<T> prev = null;
        LLNode<T> current = head;
        LLNode<T> next;
        // reverse one by one
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        head = prev;
        current = head;
        // find tail of k nodes
        while (current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(jointPoint);

        return head;
    }

    /**
     * Show the list
     * @return string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LLNode<T> curr = this;
        while (curr != null) {
            sb.append(curr.getElement()).append(" ");
            curr = curr.getNext();
        }

        return sb.toString();
    }
}