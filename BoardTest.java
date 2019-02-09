package edu.stanford.cs108.tetris;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	
	private Board b1;
	private Board b2;
	private Board b3;
	Piece pyr;
	Piece s1;
	Piece s2;
	Piece l1;
	Piece l2;
	Piece stick;
	Piece sq;
	
	@Before
	public void setUp() throws Exception {
		b1 = new Board(4,12);
		b2 = new Board(3,6);
		b3 = new Board(10,24);
		pyr = new Piece(Piece.PYRAMID_STR);
		s1 = new Piece(Piece.S1_STR);
		s2 = new Piece(Piece.S2_STR);
		l1 = new Piece(Piece.L1_STR);
		l2 = new Piece(Piece.L2_STR);
		stick = new Piece(Piece.STICK_STR);
		sq = new Piece(Piece.SQUARE_STR);
	}

	@Test
	public void test2() {
		Piece.getPieces();
		assertEquals(3, b2.getWidth());
		assertEquals(6, b2.getHeight());
		assertEquals(0, b2.dropHeight(pyr,0));
		assertEquals(0, b2.getColumnHeight(0));
		assertEquals(0, b2.getColumnHeight(1));
		assertEquals(0, b2.getColumnHeight(2));
		assertEquals(0, b2.getMaxHeight());
		
		assertEquals(1, b2.place(pyr, 0, 0));
		b2.commit();

		assertEquals(1, b2.getColumnHeight(0));
		assertEquals(2, b2.getColumnHeight(1));
		assertEquals(1, b2.getColumnHeight(2));
		assertEquals(3, b2.getRowWidth(0));
		assertEquals(1, b2.getRowWidth(1));
		assertEquals(0, b2.getRowWidth(2));
		assertEquals(2, b2.getMaxHeight());
		
		assertEquals(1, b2.dropHeight(pyr.fastRotation(),1));
		assertEquals(0, b2.place(pyr.fastRotation(), 1, 1));
		
		b2.undo();
		assertEquals(1, b2.getColumnHeight(0));
		assertEquals(2, b2.getColumnHeight(1));
		assertEquals(1, b2.getColumnHeight(2));
		assertEquals(3, b2.getRowWidth(0));
		assertEquals(1, b2.getRowWidth(1));
		assertEquals(0, b2.getRowWidth(2));
		assertEquals(2, b2.getMaxHeight());
		
		assertEquals(1, b2.dropHeight(pyr.fastRotation(),1));
		assertEquals(0, b2.place(pyr.fastRotation(), 1, 1));
		
		b2.commit();
		assertEquals(1, b2.getColumnHeight(0));
		assertEquals(3, b2.getColumnHeight(1));
		assertEquals(4, b2.getColumnHeight(2));
		assertEquals(3, b2.getRowWidth(0));
		assertEquals(2, b2.getRowWidth(1));
		assertEquals(2, b2.getRowWidth(2));
		assertEquals(1, b2.getRowWidth(3));
		assertEquals(4, b2.getMaxHeight());
		
		assertEquals(2, b2.dropHeight(pyr.fastRotation().fastRotation().fastRotation(),0));
		assertEquals(3, b2.dropHeight(pyr.fastRotation().fastRotation(),0));
		assertEquals(1, b2.place(pyr.fastRotation().fastRotation(), 0, 3));
		
		assertEquals(5, b2.getColumnHeight(0));
		assertEquals(5, b2.getColumnHeight(1));
		assertEquals(5, b2.getColumnHeight(2));
		assertEquals(3, b2.getRowWidth(0));
		assertEquals(2, b2.getRowWidth(1));
		assertEquals(2, b2.getRowWidth(2));
		assertEquals(2, b2.getRowWidth(3));
		assertEquals(3, b2.getRowWidth(4));
		assertEquals(5, b2.getMaxHeight());
	
		assertEquals(true, b2.getGrid(1, 0));
		assertEquals(true, b2.getGrid(2, 0));
		assertEquals(true, b2.getGrid(2, 1));
		assertEquals(true, b2.getGrid(1, 1));
		assertEquals(true, b2.getGrid(1, 2));
		assertEquals(true, b2.getGrid(1, 9));
		assertEquals(false, b2.getGrid(0, 2));
		
		assertEquals(2, b2.clearRows());
		assertEquals(0, b2.getColumnHeight(0));
		assertEquals(3, b2.getColumnHeight(1));
		assertEquals(3, b2.getColumnHeight(2));
		assertEquals(2, b2.getRowWidth(0));
		assertEquals(2, b2.getRowWidth(1));
		assertEquals(2, b2.getRowWidth(2));
		assertEquals(3, b2.getMaxHeight());
		
		b2.undo();
		assertEquals(1, b2.getColumnHeight(0));
		assertEquals(3, b2.getColumnHeight(1));
		assertEquals(4, b2.getColumnHeight(2));
		assertEquals(3, b2.getRowWidth(0));
		assertEquals(2, b2.getRowWidth(1));
		assertEquals(2, b2.getRowWidth(2));
		assertEquals(1, b2.getRowWidth(3));
		assertEquals(4, b2.getMaxHeight());
		
		assertEquals(2, b2.dropHeight(pyr.fastRotation().fastRotation().fastRotation(),0));
		assertEquals(3, b2.dropHeight(pyr.fastRotation().fastRotation(),0));
		assertEquals(1, b2.place(pyr.fastRotation().fastRotation(), 0, 3));
	}
	
	@Test
	public void test1() {
		
		assertEquals(0, b1.place(pyr, 0, 0));
		assertEquals(4, b1.getWidth());
		assertEquals(12, b1.getHeight());
		Piece.getPieces();
		
		assertEquals(true, b1.getGrid(0, 0));
		assertEquals(true, b1.getGrid(1, 0));
		assertEquals(true, b1.getGrid(2, 0));
		assertEquals(true, b1.getGrid(1, 1));
		assertEquals(false, b1.getGrid(1, 2));
		assertEquals(2, b1.dropHeight(stick,1));
		assertEquals(1, b1.getColumnHeight(0));
		assertEquals(2, b1.getColumnHeight(1));
		assertEquals(1, b1.getColumnHeight(2));
		assertEquals(3, b1.getRowWidth(0));
		assertEquals(1, b1.getRowWidth(1));
		assertEquals(0, b1.getRowWidth(2));
		assertEquals(0, b1.getRowWidth(3));
		assertEquals(2, b1.getMaxHeight());
		
		b1.undo();
		assertEquals(0, b1.place(pyr, 0, 0));
		assertEquals(true, b1.getGrid(0, 0));
		assertEquals(true, b1.getGrid(1, 0));
		assertEquals(true, b1.getGrid(2, 0));
		assertEquals(true, b1.getGrid(1, 1));
		assertEquals(false, b1.getGrid(1, 2));
		assertEquals(2, b1.dropHeight(stick,1));
		assertEquals(1, b1.getColumnHeight(0));
		assertEquals(2, b1.getColumnHeight(1));
		assertEquals(1, b1.getColumnHeight(2));
		assertEquals(3, b1.getRowWidth(0));
		assertEquals(1, b1.getRowWidth(1));
		assertEquals(0, b1.getRowWidth(2));
		assertEquals(0, b1.getRowWidth(3));
		assertEquals(2, b1.getMaxHeight());
		
		b1.commit();
		assertEquals(0, b1.place(stick, 1, 2));
		assertEquals(6, b1.dropHeight(pyr.fastRotation(),0));
		assertEquals(1, b1.getColumnHeight(0));
		assertEquals(6, b1.getColumnHeight(1));
		assertEquals(1, b1.getColumnHeight(2));
		assertEquals(3, b1.getRowWidth(0));
		assertEquals(1, b1.getRowWidth(1));
		assertEquals(1, b1.getRowWidth(2));
		assertEquals(1, b1.getRowWidth(3));
		assertEquals(1, b1.getRowWidth(4));
		assertEquals(1, b1.getRowWidth(5));
		assertEquals(0, b1.getRowWidth(6));
		assertEquals(6, b1.getMaxHeight());
		
		b1.commit();
		assertEquals(0, b1.place(pyr.fastRotation(), 0, 6));
		assertEquals(8, b1.dropHeight(s2,1));
		assertEquals(8, b1.getColumnHeight(0));
		assertEquals(9, b1.getColumnHeight(1));
		assertEquals(1, b1.getColumnHeight(2));
		assertEquals(0, b1.getColumnHeight(3));
		assertEquals(3, b1.getRowWidth(0));
		assertEquals(1, b1.getRowWidth(1));
		assertEquals(1, b1.getRowWidth(2));
		assertEquals(1, b1.getRowWidth(3));
		assertEquals(1, b1.getRowWidth(4));
		assertEquals(1, b1.getRowWidth(5));
		assertEquals(1, b1.getRowWidth(6));
		assertEquals(2, b1.getRowWidth(7));
		assertEquals(1, b1.getRowWidth(8));
		assertEquals(0, b1.getRowWidth(9));
		assertEquals(9, b1.getMaxHeight());
		
		b1.commit();
		assertEquals(0, b1.place(s2, 1, 8));
		assertEquals(8, b1.dropHeight(l2.fastRotation().fastRotation(),0));
		assertEquals(8, b1.getColumnHeight(0));
		assertEquals(10, b1.getColumnHeight(1));
		assertEquals(10, b1.getColumnHeight(2));
		assertEquals(9, b1.getColumnHeight(3));
		assertEquals(3, b1.getRowWidth(0));
		assertEquals(1, b1.getRowWidth(1));
		assertEquals(1, b1.getRowWidth(2));
		assertEquals(1, b1.getRowWidth(3));
		assertEquals(1, b1.getRowWidth(4));
		assertEquals(1, b1.getRowWidth(5));
		assertEquals(1, b1.getRowWidth(6));
		assertEquals(2, b1.getRowWidth(7));
		assertEquals(3, b1.getRowWidth(8));
		assertEquals(2, b1.getRowWidth(9));
		assertEquals(10, b1.getMaxHeight());
		
		b1.undo();
		assertEquals(8, b1.dropHeight(s2,1));
		assertEquals(8, b1.getColumnHeight(0));
		assertEquals(9, b1.getColumnHeight(1));
		assertEquals(1, b1.getColumnHeight(2));
		assertEquals(0, b1.getColumnHeight(3));
		assertEquals(3, b1.getRowWidth(0));
		assertEquals(1, b1.getRowWidth(1));
		assertEquals(1, b1.getRowWidth(2));
		assertEquals(1, b1.getRowWidth(3));
		assertEquals(1, b1.getRowWidth(4));
		assertEquals(1, b1.getRowWidth(5));
		assertEquals(1, b1.getRowWidth(6));
		assertEquals(2, b1.getRowWidth(7));
		assertEquals(1, b1.getRowWidth(8));
		assertEquals(0, b1.getRowWidth(9));
		assertEquals(9, b1.getMaxHeight());
		
		assertEquals(0, b1.place(s2, 1, 8));
		assertEquals(8, b1.dropHeight(l2.fastRotation().fastRotation(),0));
		assertEquals(8, b1.getColumnHeight(0));
		assertEquals(10, b1.getColumnHeight(1));
		assertEquals(10, b1.getColumnHeight(2));
		assertEquals(9, b1.getColumnHeight(3));
		assertEquals(3, b1.getRowWidth(0));
		assertEquals(1, b1.getRowWidth(1));
		assertEquals(1, b1.getRowWidth(2));
		assertEquals(1, b1.getRowWidth(3));
		assertEquals(1, b1.getRowWidth(4));
		assertEquals(1, b1.getRowWidth(5));
		assertEquals(1, b1.getRowWidth(6));
		assertEquals(2, b1.getRowWidth(7));
		assertEquals(3, b1.getRowWidth(8));
		assertEquals(2, b1.getRowWidth(9));
		assertEquals(10, b1.getMaxHeight());
		b1.commit();
		
		assertEquals(1, b1.place(l2.fastRotation().fastRotation(), 0, 8));
		assertEquals(11, b1.dropHeight(stick.fastRotation(),0));
		assertEquals(11, b1.getColumnHeight(0));
		assertEquals(11, b1.getColumnHeight(1));
		assertEquals(10, b1.getColumnHeight(2));
		assertEquals(9, b1.getColumnHeight(3));
		assertEquals(3, b1.getRowWidth(0));
		assertEquals(1, b1.getRowWidth(1));
		assertEquals(1, b1.getRowWidth(2));
		assertEquals(1, b1.getRowWidth(3));
		assertEquals(1, b1.getRowWidth(4));
		assertEquals(1, b1.getRowWidth(5));
		assertEquals(1, b1.getRowWidth(6));
		assertEquals(2, b1.getRowWidth(7));
		assertEquals(4, b1.getRowWidth(8));
		assertEquals(3, b1.getRowWidth(9));
		assertEquals(2, b1.getRowWidth(10));
		assertEquals(11, b1.getMaxHeight());
	
		b1.commit();
		
		assertEquals(1, b1.place(stick.fastRotation(), 0, 11));
		System.out.println(b1.toString2());
		assertEquals(12, b1.getColumnHeight(0));
		assertEquals(12, b1.getColumnHeight(1));
		assertEquals(12, b1.getColumnHeight(2));
		assertEquals(12, b1.getColumnHeight(3));
		assertEquals(3, b1.getRowWidth(0));
		assertEquals(1, b1.getRowWidth(1));
		assertEquals(1, b1.getRowWidth(2));
		assertEquals(1, b1.getRowWidth(3));
		assertEquals(1, b1.getRowWidth(4));
		assertEquals(1, b1.getRowWidth(5));
		assertEquals(1, b1.getRowWidth(6));
		assertEquals(2, b1.getRowWidth(7));
		assertEquals(4, b1.getRowWidth(8));
		assertEquals(3, b1.getRowWidth(9));
		assertEquals(2, b1.getRowWidth(10));
		assertEquals(4, b1.getRowWidth(11));
		assertEquals(12, b1.getMaxHeight());
		
		b1.clearRows();
		System.out.println(b1.toString2());
		
		b1.undo();
		assertEquals(1, b1.place(stick.fastRotation(), 0, 11));
		System.out.println(b1.toString2());
		assertEquals(12, b1.getColumnHeight(0));
		assertEquals(12, b1.getColumnHeight(1));
		assertEquals(12, b1.getColumnHeight(2));
		assertEquals(12, b1.getColumnHeight(3));
		assertEquals(3, b1.getRowWidth(0));
		assertEquals(1, b1.getRowWidth(1));
		assertEquals(1, b1.getRowWidth(2));
		assertEquals(1, b1.getRowWidth(3));
		assertEquals(1, b1.getRowWidth(4));
		assertEquals(1, b1.getRowWidth(5));
		assertEquals(1, b1.getRowWidth(6));
		assertEquals(2, b1.getRowWidth(7));
		assertEquals(4, b1.getRowWidth(8));
		assertEquals(3, b1.getRowWidth(9));
		assertEquals(2, b1.getRowWidth(10));
		assertEquals(4, b1.getRowWidth(11));
		assertEquals(12, b1.getMaxHeight());
		
		b1.clearRows();
		System.out.println(b1.toString2());
		b1.commit();

	}
	
	@Test
	public void test3() {
		assertEquals(0, b3.dropHeight(sq, 4));
	}
}
