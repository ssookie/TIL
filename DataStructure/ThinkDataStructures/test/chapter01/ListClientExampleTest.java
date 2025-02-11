package chapter01;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListClientExampleTest extends TestCase {

    @Test
    public void testGetList() {
        // given
        ListClientExample lce = new ListClientExample();

        // when
        List list = lce.getList();

        // then
        assertThat(list, instanceOf(ArrayList.class) );
    }
}