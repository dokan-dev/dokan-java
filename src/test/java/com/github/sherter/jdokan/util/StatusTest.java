package com.github.sherter.jdokan.util;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class StatusTest {

	@Test
	public void returnCodeZeroIsSuccess() {
		assertThat(Status.from(0)).isSameAs(Status.SUCCESS);
	}

	@Test
	public void returnCodeMinusFiveIsMountError() {
		assertThat(Status.from(-5)).isSameAs(Status.MOUNT_ERROR);
	}

	@Test
	public void randomUnkownReturnCodeIsUnknown() throws Exception {
		Set<Integer> known = Arrays.stream(Status.values()).map(s -> s.exitCode)
				.collect(Collectors.toSet());
		int unknownReturnValue = new Random().ints().filter(v -> !known.contains(v)).findFirst()
				.getAsInt();
		assertThat(Status.from(unknownReturnValue)).isSameAs(Status.UNKNOWN);
	}

}
