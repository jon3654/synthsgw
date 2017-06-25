package com.github.synthsgw.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.OpenFile;
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
    public void CheckExtWithCorrectExtension(){
        OpenFile file = new OpenFile("mp3");
        assertEquals(file.checkExt("mp3"), 0);
    }
    
    @Test
    public void CheckExtWithoutCorrectExtension(){
        OpenFile file = new OpenFile("mp3");
        assertEquals(file.checkExt("asd"),-1);
    }
    
    @Test
    public void AttemptToCloseFileWhileNoneIsOpen(){
        OpenFile file = new OpenFile("mp3");
        assertEquals(file.close(), -1);
    }
    
}
