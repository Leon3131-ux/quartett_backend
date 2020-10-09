package com.nickleback.quartettBackend.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class InviteCodeGeneratorUT {

    @Test
    public void testMaxInviteCodeStrength(){
        InviteCodeGenerator inviteCodeGenerator = new InviteCodeGenerator();
        String generatedInviteCode = inviteCodeGenerator.generateInviteCode("foo", 9);
        assertTrue(generatedInviteCode.contains("foo"));
        assertEquals(12, generatedInviteCode.length());
    }

    @Test
    public void testMinInviteCodeStrength(){
        InviteCodeGenerator inviteCodeGenerator = new InviteCodeGenerator();
        String generatedInviteCode = inviteCodeGenerator.generateInviteCode("foo", 0);
        assertEquals("foo", generatedInviteCode);
    }

    @Test
    public void testEmptyString(){
        InviteCodeGenerator inviteCodeGenerator = new InviteCodeGenerator();
        String generatedInviteCode = inviteCodeGenerator.generateInviteCode("", 1);
        assertEquals(1, generatedInviteCode.length());
        int generatedInviteCodeInt = Integer.parseInt(generatedInviteCode);
        assertTrue(generatedInviteCodeInt >= 1 && generatedInviteCodeInt < 10);
    }

    @Test
    public void testNullString(){
        InviteCodeGenerator inviteCodeGenerator = new InviteCodeGenerator();
        String generatedInviteCode = inviteCodeGenerator.generateInviteCode(null, 0);
        assertNull(generatedInviteCode);
    }

    @Test
    public void testIllegalArgumentException(){
        InviteCodeGenerator inviteCodeGenerator = new InviteCodeGenerator();

        assertThrows(IllegalArgumentException.class, () -> inviteCodeGenerator.generateInviteCode("foo", -1));
        assertThrows(IllegalArgumentException.class, () -> inviteCodeGenerator.generateInviteCode("foo", 10));
    }



}
