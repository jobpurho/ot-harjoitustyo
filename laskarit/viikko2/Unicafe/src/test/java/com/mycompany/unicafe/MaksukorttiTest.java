package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void saldoOnOikein() {
        assertEquals(10, kortti.saldo());      
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(5);
        assertEquals("saldo: "+0+"."+5, kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaEiRiita() {
        kortti.otaRahaa(20);
        assertEquals("saldo: "+0+"."+10, kortti.toString());
    }

    @Test
    public void palauttaaTrueKunRahatRiittaaJaFalseKunEiRiita() {
        
        assertTrue(kortti.otaRahaa(10));
        assertTrue(!kortti.otaRahaa(10));
    }

    @Test
    public void lataaRahaaLisaaOikeanSumman() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: "+0+"."+20, kortti.toString());
    }
}
