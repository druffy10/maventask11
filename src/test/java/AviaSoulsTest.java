import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AviaSoulsTest {
    private Ticket t1, t2, t3, t4;
    private AviaSouls aviaSouls;
    private TicketTimeComparator comparator;

    @BeforeEach
    public void setUp() {
        t1 = new Ticket("Moscow", "New York", 600, 8, 16);
        t2 = new Ticket("Moscow", "Paris", 300, 12, 18);
        t3 = new Ticket("Paris", "New York", 500, 11, 14);
        t4 = new Ticket("Moscow", "New York", 500, 10, 12);
        aviaSouls = new AviaSouls();
        aviaSouls.add(t1);
        aviaSouls.add(t2);
        aviaSouls.add(t3);
        aviaSouls.add(t4);
        comparator = new TicketTimeComparator();
    }

    @Test
    public void testSearchNoResults() {
        Ticket[] expected = new Ticket[0];
        Ticket[] actual = aviaSouls.search("Paris", "Moscow");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchOneResult() {
        Ticket[] expected = new Ticket[]{t2};
        Ticket[] actual = aviaSouls.search("Moscow", "Paris");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchMultipleResults() {
        Ticket[] expected = new Ticket[]{t4, t1};
        Ticket[] actual = aviaSouls.search("Moscow", "New York");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testCompareSamePrice() {
        int expected = 0;
        int actual = t3.compareTo(t4);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCompareLowerPrice() {
        int expected = -1;
        int actual = t2.compareTo(t1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCompareHigherPrice() {
        int expected = 1;
        int actual = t3.compareTo(t2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSearchAndSortByTime() {
        Ticket[] expected = {t4, t1};
        Ticket[] actual = aviaSouls.searchAndSortBy("Moscow", "New York", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
