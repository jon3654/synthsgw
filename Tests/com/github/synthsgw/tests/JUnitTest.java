package com.github.synthsgw.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.github.synthsgw.functionality.OpenFile;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitTest {
        
    public JUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void CheckExtWithCorrectExtension_test(){
        OpenFile file = new OpenFile("mp3");
        assertEquals(file.checkExt("mp3"), 0);
    }
    
    @Test
    public void CheckExtWithoutCorrectExtension_test(){
        OpenFile file = new OpenFile("mp3");
        assertEquals(file.checkExt("asd"),-1);
    }
    
}
