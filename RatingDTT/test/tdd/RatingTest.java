/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdd;

import data.Rating;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author NoteBook
 */
public class RatingTest {

//    public RatingTest() {
//    }
    //ta se viet cac ham de test cac doan code, test
    //cac method o code chinh cua ta, tuc la ta se
    //dua data cho code chinh, dua data cho
    //Rating.rate() va ta cho code chinh xu li
    //output ket qua, ta so ket qua cua code chinh
    //voi ket qua ta mong no tra ve (expected so voi actual)
    //neu khong khop no canh bao mau do
    //neu ngon no canh bao mau xanh theo bo test
    //de cac ham o day chay dc, ta can khai bao annotation
    //@Test truoc ten ham
    @Test
    public void testRating() {
        assertEquals("Excellence", Rating.rate(10));
        assertEquals("Good", Rating.rate(8));
        assertEquals("Fair", Rating.rate(7));
        assertEquals("Average", Rating.rate(5));
        assertEquals("Bad", Rating.rate(4));
        assertEquals("Invalid Grade", Rating.rate(40));
        assertEquals("Invalid Grade", Rating.rate(-1));
    }
    
}









