package com.qa.awl.domain;

import javax.persistence.Id;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.qa.awl.domain.AWL.AWLBuilder;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AWLDomainTest {

	@Test
	public void testEqualsAWL() {
		EqualsVerifier.simple().forClass(AWL.class).withIgnoredAnnotations(Id.class).verify();

	}

	@Test
	public void testAllArgConstructor() {
		AWL awl1 = new AWL(1L, "OPM", 12, 5);
		AWL awl2 = new AWL(1L, "OPM", 12, 5);
		AWL awl3 = new AWL("OPM", 7, 4);
		Assert.assertNotNull(awl1);
		Assert.assertNotNull(awl2);
		Assert.assertEquals(awl1, awl2);
		Assert.assertNotEquals(awl2, awl3);
		Assert.assertNotEquals(awl1, awl3);
	}

	@Test
	public void testBuilder() {
		AWLBuilder awlBuilder = AWL.builder().name("OPM").id(1L).episode(4).rating(7);
		AWL awl1 = awlBuilder.build();
		Assert.assertNotNull(awl1);
		Assert.assertEquals(awl1.getName(), "OPM");
	}

}
