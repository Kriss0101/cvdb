package cvdb.api.util;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class CollectionUtil {

	public static <T> LinkedHashSet<T> asLinkedHashSet(T...elements) {
		return new LinkedHashSet<T>(Arrays.asList(elements));
	}
}
