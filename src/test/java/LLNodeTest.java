import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.LLNode;

/**
 * Test for LLNode
 * @author Katherine Chen
 */
public class LLNodeTest {
    // head of linked list
    private LLNode<Integer> head;

    /**
     * Setup before each test
     */
    @BeforeEach
    public void setUp() {
       initHead();
    }

    /**
     * Initialize linked list
     * @return head of linked list
     */
    public LLNode<Integer> initHead() {
        head = new LLNode<>(1, null);
        LLNode<Integer> curr = head;
        for (int i = 2; i <= 10; i++) {
            LLNode<Integer> node = new LLNode<>(i, null);
            curr.setNext(node);
            curr = node;
        }

        return head;
    }

    /**
     * Test move lead back
     */
    @Test
    public void testMoveLeadBack() {
        Assertions.assertEquals("1 2 3 4 5 6 7 8 9 10 ", head.moveBack(0).toString());
        initHead();
        Assertions.assertEquals("1 2 3 4 5 6 7 8 9 10 ", head.moveBack(10).toString());
        initHead();
        Assertions.assertEquals("2 1 3 4 5 6 7 8 9 10 ", head.moveBack(1).toString());
        initHead();
        Assertions.assertEquals("2 3 1 4 5 6 7 8 9 10 ", head.moveBack(2).toString());
        initHead();
        Assertions.assertEquals("2 3 4 1 5 6 7 8 9 10 ", head.moveBack(3).toString());
        initHead();
        Assertions.assertEquals("2 3 4 5 1 6 7 8 9 10 ", head.moveBack(4).toString());
    }

    /**
     * test move first to last
     */
    @Test
    public void testMoveFirstToLast() {
        Assertions.assertEquals("2 3 4 5 6 7 8 9 10 1 ", head.moveFirstToLast().toString());
    }

    /**
     * test move last to first
     */
    @Test
    public void testMoveLastToFirst() {
        Assertions.assertEquals("10 1 2 3 4 5 6 7 8 9 ", head.moveLastToFirst().toString());
    }

    /**
     * test reverse list
     */
    @Test
    public void testReverseList() {
        Assertions.assertEquals("10 9 8 7 6 5 4 3 2 1 ", head.reverseList().toString());
    }

    /**
     * test reverse first k
     */
    @Test
    public void testReverseFirstK() {
        Assertions.assertEquals("5 4 3 2 1 6 7 8 9 10 ", head.reverseFirstK(5).toString());
    }
}
