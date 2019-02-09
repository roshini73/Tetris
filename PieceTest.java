package edu.stanford.cs108.tetris;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	private Piece pyr1, pyr2, pyr3, pyr4;
	private Piece pyrA, pyrB, pyrC, pyrD;
	private Piece l, l1;
	private Piece s, s1;
	private Piece sq, sq1;

	@Before
	public void setUp() throws Exception {
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = new Piece("0 1 1 0 1 1 1 2");
		pyr3 = new Piece("0 1 1 0 1 1 2 1");
		pyr4 = new Piece("0 0 0 1 0 2 1 1");
		
		pyrA = new Piece(Piece.L1_STR);
		pyrB = new Piece("0 0 1 0 2 0 2 1");
		pyrC = new Piece("1 0 1 1 1 2 0 2");
		pyrD = new Piece("0 0 0 1 1 1 2 1");
		
		l = new Piece(Piece.STICK_STR);
		l1 = new Piece("0 0 1 0 2 0 3 0");
		
		s = new Piece(Piece.S1_STR);
		s1 = new Piece("0 1 0 2 1 0 1 1");
		
		sq = new Piece(Piece.SQUARE_STR);
		sq1 = new Piece("0 0 0 1 1 0 1 1");
	}
	
	@Test
	public void testWidth() {
		//simple test of getWidth()
		assertEquals(3, pyr1.getWidth());
		//test if rotated properly
		assertEquals(2, pyr1.fastRotation().getWidth());
		assertEquals(3, pyr1.fastRotation().fastRotation().getWidth());
		assertEquals(2, pyr1.fastRotation().fastRotation().fastRotation().getWidth());
		
		//simple test of getWidth()
		assertEquals(2, pyrA.getWidth());
		//test if rotated properly
		assertEquals(3, pyrA.fastRotation().getWidth());
		assertEquals(2, pyrA.fastRotation().fastRotation().getWidth());
		assertEquals(3, pyrA.fastRotation().fastRotation().fastRotation().getWidth());		
		
		//simple test of getWidth()
		assertEquals(1, l.getWidth());
		//test if rotated properly
		assertEquals(4, l.fastRotation().getWidth());
		
		//simple test of getWidth()
		assertEquals(3, s.getWidth());
		//test if rotated properly
		assertEquals(2, s.fastRotation().getWidth());
		
		//simple test of getWidth()
		assertEquals(2, sq.getWidth());
		//test if rotated properly
		assertEquals(2, sq.fastRotation().getWidth());
	}
	
	@Test
	public void testHeight() {
		//simple test of getHeight()
		assertEquals(2, pyr1.getHeight());
		//test if rotated properly
		assertEquals(3, pyr1.fastRotation().getHeight());
		assertEquals(2, pyr1.fastRotation().fastRotation().getHeight());
		assertEquals(3, pyr1.fastRotation().fastRotation().fastRotation().getHeight());

		//simple test of getHeight()
		assertEquals(3, pyrA.getHeight());
		//test if rotated properly
		assertEquals(2, pyrA.fastRotation().getHeight());
		assertEquals(3, pyrA.fastRotation().fastRotation().getHeight());
		assertEquals(2, pyrA.fastRotation().fastRotation().fastRotation().getHeight());
		
		//simple test of getHeight()
		assertEquals(4, l.getHeight());
		//test if rotated properly
		assertEquals(1, l.fastRotation().getHeight());
		
		//simple test of getHeight()
		assertEquals(2, s.getHeight());
		//test if rotated properly
		assertEquals(3, s.fastRotation().getHeight());
		
		//simple test of getHeight()
		assertEquals(2, sq.getHeight());
		//test if rotated properly
		assertEquals(2, sq.fastRotation().getHeight());
	}
	
	@Test
	public void testSampleSkirt() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.
		
		//simple test of getSkirt()
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		//tests of getSkirt() on rotated pieces
		assertTrue(Arrays.equals(new int[] {1, 0}, pyr2.getSkirt()));
		assertTrue(Arrays.equals(pyr2.getSkirt(), pyr1.fastRotation().getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
		assertTrue(Arrays.equals(pyr3.getSkirt(), pyr1.fastRotation().fastRotation().getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 1}, pyr4.getSkirt()));
		assertTrue(Arrays.equals(pyr4.getSkirt(), pyr1.fastRotation().fastRotation().fastRotation().getSkirt()));
		
		//simple test of getSkirt()
		assertTrue(Arrays.equals(new int[] {0, 0}, pyrA.getSkirt()));
		//tests of getSkirt() on rotated pieces
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyrB.getSkirt()));
		assertTrue(Arrays.equals(pyrB.getSkirt(), pyrA.fastRotation().getSkirt()));
		assertTrue(Arrays.equals(new int[] {2, 0}, pyrC.getSkirt()));
		assertTrue(Arrays.equals(pyrC.getSkirt(), pyrA.fastRotation().fastRotation().getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 1, 1}, pyrD.getSkirt()));
		assertTrue(Arrays.equals(pyrD.getSkirt(), pyrA.fastRotation().fastRotation().fastRotation().getSkirt()));
		
		//simple test of getSkirt()
		assertTrue(Arrays.equals(new int[] {0}, l.getSkirt()));
		//test of getSkirt() on rotated piece
		assertTrue(Arrays.equals(new int[] {0, 0, 0, 0}, l1.getSkirt()));
		assertTrue(Arrays.equals(l1.getSkirt(), l.fastRotation().getSkirt()));
		
		//simple test of getSkirt()
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s.getSkirt()));
		//test of getSkirt() on rotated piece
		assertTrue(Arrays.equals(new int[] {1, 0}, s1.getSkirt()));
		assertTrue(Arrays.equals(s1.getSkirt(), s.fastRotation().getSkirt()));
		
		//simple test of getSkirt()
		assertTrue(Arrays.equals(new int[] {0, 0}, sq.getSkirt()));
		//simple test of getSkirt()
		assertTrue(Arrays.equals(new int[] {0, 0}, sq1.getSkirt()));
		assertTrue(Arrays.equals(sq1.getSkirt(), sq.fastRotation().getSkirt()));
	}
	
	@Test
	public void testFastRotation() {
		Piece.getPieces();
		//simple test of fastRotation()
		assertEquals(sq1, sq.fastRotation());
		
		//comprehensive test of fastRotation()
		assertEquals(pyr2, pyr1.fastRotation());
		assertEquals(pyr3, pyr2.fastRotation());
		assertEquals(pyr3, pyr1.fastRotation().fastRotation());
		assertEquals(pyr4, pyr3.fastRotation());
		assertEquals(pyr4, pyr2.fastRotation().fastRotation());
		assertEquals(pyr4, pyr1.fastRotation().fastRotation().fastRotation());
		
		//comprehensive test of fastRotation()
		assertEquals(pyrB, pyrA.fastRotation());
		assertEquals(pyrC, pyrB.fastRotation());
		assertEquals(pyrC, pyrA.fastRotation().fastRotation());
		assertEquals(pyrD, pyrC.fastRotation());
		assertEquals(pyrD, pyrB.fastRotation().fastRotation());
		assertEquals(pyrD, pyrA.fastRotation().fastRotation().fastRotation());	
		
		//comprehensive test of fastRotation()
		assertEquals(l1, l.fastRotation());
		assertEquals(l, l1.fastRotation());
		assertEquals(l, l.fastRotation().fastRotation());
		assertEquals(l1, l1.fastRotation().fastRotation());
		assertEquals(l1, l.fastRotation().fastRotation().fastRotation());
		assertEquals(l, l1.fastRotation().fastRotation().fastRotation());	
		
		//comprehensive test of fastRotation()
		assertEquals(s1, s.fastRotation());
		assertEquals(s, s1.fastRotation());
		assertEquals(s, s.fastRotation().fastRotation());
		assertEquals(s1, s1.fastRotation().fastRotation());
		assertEquals(s1, s.fastRotation().fastRotation().fastRotation());
		assertEquals(s, s1.fastRotation().fastRotation().fastRotation());	
		
		//comprehensive test of fastRotation()
		assertEquals(sq1, sq.fastRotation());
		assertEquals(sq, sq1.fastRotation());
		assertEquals(sq1, sq.fastRotation().fastRotation());
		assertEquals(sq, sq1.fastRotation().fastRotation());
		assertEquals(sq, sq1.fastRotation().fastRotation().fastRotation());	
		assertEquals(sq1, sq.fastRotation().fastRotation().fastRotation());
	}
	
	
}
