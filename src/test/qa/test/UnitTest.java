package qa.test;

import org.junit.Test;

import qa.test.BoundedBuffer;

public class UnitTest {

	@Test
	public static void main(String[] args) {
		
		BoundedBuffer bb = new BoundedBuffer();
		
		for ( i =0; i < 50 ; i++ ) {
			bb.put( (String)(i + "hello") );
		}
		
		for ( i =0; i < 50 ; i++ ) {
			String x = (String)bb.take();
			System.out.println(x);
		}

	}

}
