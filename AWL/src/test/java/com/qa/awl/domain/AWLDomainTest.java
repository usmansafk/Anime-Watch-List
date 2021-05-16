package com.qa.awl.domain;

import javax.persistence.Id;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class AWLDomainTest {

	@Test
	public void testEqualsAWL() {
		EqualsVerifier.simple().forClass(AWL.class).withIgnoredAnnotations(Id.class).verify();

	}

}
