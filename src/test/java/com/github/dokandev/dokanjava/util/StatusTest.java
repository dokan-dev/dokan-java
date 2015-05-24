package com.github.dokandev.dokanjava.util;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.github.dokandev.dokanjava.util.DokanStatus;

public class StatusTest {

	@Test
	public void returnCodeZeroIsSuccess() {
		assertThat(DokanStatus.fromInt(0)).isSameAs(DokanStatus.SUCCESS);
	}

	@Test
	public void returnCodeMinusFiveIsMountError() {
		assertThat(DokanStatus.fromInt(-5)).isSameAs(DokanStatus.MOUNT_ERROR);
	}

	@Test
	public void randomUnkownReturnCodeIsUnknown() throws Exception {
		Set<Integer> known = Arrays.stream(DokanStatus.values()).map(s -> s.code())
				.collect(Collectors.toSet());
		int unknownReturnValue = new Random().ints().filter(v -> !known.contains(v)).findFirst()
				.getAsInt();
		assertThat(DokanStatus.fromInt(unknownReturnValue)).isSameAs(DokanStatus.UNKNOWN);
	}

}
