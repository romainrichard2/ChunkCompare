import java.util.Comparator;

public abstract class ChunkComparator<T> implements Comparator<T> {
	protected int compare(String s1, String s2) {
		// if one of these is null, return 0
		if (null == s1 || null == s2) {
			return 0;
		}

		// check for each chunk of data to compare
		int s1Marker = 0;
		int s2Marker = 0;

		while (s1Marker < s1.length() && s2Marker < s2.length()) {
			String s1Chunk = getChunk(s1, s1.length(), s1Marker);
			s1Marker += s1Chunk.length();

			String s2Chunk = getChunk(s2, s2.length(), s2Marker);
			s2Marker += s2Chunk.length();

			// if both of them contains digits, compare those chunks as numeric values
			int result = 0;
			if (isDigit(s1Chunk.charAt(0)) && isDigit(s2Chunk.charAt(0))) {
				result = Integer.compare(Integer.parseInt(s1Chunk), Integer.parseInt(s2Chunk));
			} else // Non numeric chunks
			{
				result = s1Chunk.compareTo(s2Chunk);
			}

			if (result != 0) {
				return result;
			}
		}

		return s1.length() - s2.length();
	}

	// Little char
	// I must tell you... I... Love... your sister String !
	// 'cause I just hate ASCII code
	private boolean isDigit(char pChar) {
		return (pChar >= 48 && pChar <= 57);
	}

	/**
	 * Create a chunk: A substring of the String with a single type (Digit or alpha)
	 * 
	 * @param pString       Original string to split in different chunks
	 * @param pStringLength The length of pString (boring to add .length()
	 *                      everywhere)
	 * @param pMarker       My beautiful iterator to check each char of the string
	 * @return A single chunk
	 */
	private String getChunk(String pString, int pStringLength, int pMarker) {
		StringBuilder lChunk = new StringBuilder();

		char c = pString.charAt(pMarker);
		lChunk.append(c);

		pMarker++;

		boolean isDigit = this.isDigit(c);

		while (pMarker < pStringLength) {
			c = pString.charAt(pMarker);
			if (isDigit != this.isDigit(c)) {
				break; // my heart !
			}
			lChunk.append(c);
			pMarker++;
		}

		return lChunk.toString();
	}
}

