/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jobpurho
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    public KassapaateTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(640);
    }
    
    
    @After
    public void tearDown() {
    }
    

    @Test
    public void rahaMaaraJaMyytyjenLounaidenMaaraOnOikein() {
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }    

    @Test
    public void syoEdullisestiToimii() {
        paate.syoEdullisesti(240);
        assertEquals(100240, paate.kassassaRahaa());
        paate.syoEdullisesti(220);
        assertEquals(100240, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    } 
    
    @Test
    public void syoMaukkaastiToimii() {
        paate.syoMaukkaasti(400);
        assertEquals(100400, paate.kassassaRahaa());
        paate.syoMaukkaasti(200);
        assertEquals(100400, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    } 
    
    @Test
    public void palauttaaTrueJosMaksuRiittaa() {
        assertTrue(paate.syoMaukkaasti(kortti));
        assertTrue(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void palauttaaFalseJosMaksuEiRiita() {
        kortti=new Maksukortti(200);
        assertTrue(!paate.syoMaukkaasti(kortti));        
        assertTrue(!paate.syoEdullisesti(kortti));
    }    
    
    @Test
    public void onnistunutKorttiostoVeloittaaRahaa() {
        assertTrue(paate.syoEdullisesti(kortti));
        assertEquals(400, kortti.saldo());
        assertTrue(paate.syoMaukkaasti(kortti));
        assertEquals(0, kortti.saldo());        
    }
    
    @Test
    public void epaonnistunutKorttiostoEiVeloitaRahaa() {
        kortti = new Maksukortti(200);
        assertTrue(!paate.syoEdullisesti(kortti));
        assertEquals(200, kortti.saldo());
        assertTrue(!paate.syoMaukkaasti(kortti));
        assertEquals(200, kortti.saldo());        
    }
    
    @Test
    public void korttiOstosEiVaikutaKassaan() {
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());   
    }
    
    @Test
    public void latausKasvattaaKortinJaKassanRahamaaraa() {
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, paate.kassassaRahaa());
        assertEquals(740, kortti.saldo());
    }
    @Test
    public void negatiivistaSummaEiVoiLadata() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(640, kortti.saldo());
    }    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
