package dabble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import dict.Dictionary;

/**
 * A class that models the state of the puzzle game Dabble.
 * 
 * <p>
 * The game Dabble is made up of five English words of lengths 2, 3, 4, 5, and 6
 * (these five words make up a solution for the puzzle). The letters of the all
 * of the words are randomly scrambled together to produce five scrambled puzzle
 * words of lengths 2, 3, 4, 5, and 6. For example, the five solution words:
 * 
 * <p>
 * {@code it, you, here, batch, burner}
 * 
 * <p>
 * might produce the five scrambled words:
 * 
 * <p>
 * {@code un, uei, ytrc, bahrt, obrehe}
 * 
 * <p>
 * A player attempts to unscramble the words by repeatedly exchanging a letter
 * from one scrambled word with the letter in a second scrambled word.
 * 
 * <p>
 * The puzzle is solved when the player has formed five English words of lengths
 * 2, 3, 4, 5, and 6 (not necessarily the same words as the original solution
 * because there is often multiple solutions).
 *
 */
public class Dabble {

	private Map<Integer, String> solution;
	private Map<Integer, String> scrambled;

	/**
	 * The dictionary used by the class.
	 */
	public static final Dictionary DICT = new Dictionary();

	/**
	 * The shortest word length in the game.
	 */
	public static final int MIN_WORD_LENGTH = 2;

	/**
	 * The longest word length in the game.
	 */
	public static final int MAX_WORD_LENGTH = 6;

	/**
	 * The number of words in the game.
	 */
	public static final int NUMBER_OF_WORDS = 5;

	/**
	 * Initializes this dabble to a specific set of scrambled and solution words for
	 * debugging and testing purposes.
	 * 
	 * @param notUsed not used, included so that this constructor would have a
	 *                different signature than the other constructors
	 */
	public Dabble(int notUsed) {
		this.solution = new TreeMap<>();
		this.scrambled = new TreeMap<>();

		this.solution.put(2, "ad");
		this.solution.put(3, "bet");
		this.solution.put(4, "cook");
		this.solution.put(5, "dumps");
		this.solution.put(6, "eclair");

		this.scrambled.put(2, "ri");
		this.scrambled.put(3, "alc");
		this.scrambled.put(4, "espm");
		this.scrambled.put(5, "udkoo");
		this.scrambled.put(6, "ctebad");
	}

	/**
	 * Initialize the words of the game by choosing random words from a dictionary.
	 */
	public Dabble() {
		String[] words = new String[Dabble.NUMBER_OF_WORDS];
		
		for (int i = 0; i < words.length; i++) {
			int len = i + Dabble.MIN_WORD_LENGTH;
			words[i] = FindRandomWord(len);
		}
		
		this.solution = new TreeMap<>();
		this.scrambled = new TreeMap<>();
		
		for (int i = Dabble.MIN_WORD_LENGTH; i <= Dabble.MAX_WORD_LENGTH; i++) {
			int index = i - Dabble.MIN_WORD_LENGTH;
			if (words[index].length() != i) {
				throw new IllegalArgumentException("expected length of word: " + i + ", got: " + words[index].length());
			}
			
			this.solution.put(i, words[index]);
		}
		
		this.scrambled = ScrambleWords(this.solution);
	}

	/**
	 * Initialize the words of the game by using the specified words.
	 * 
	 * <p>
	 * There must be exactly {@code NUMBER_OF_WORDS} strings in the {@code words}
	 * otherwise an exception is thrown. Furthermore, the strings must be in
	 * ascending order of length from {@code MIN_WORD_LENGTH, MIN_WORD_LENGTH + 1,
	 * MIN_WORD_LENGTH + 2, ... , MAX_WORD_LENGTH}. Finally, the strings must all be
	 * contained in the dictionary used by the class.
	 * 
	 * @param words an array of NUMBER_OF_WORDS strings in ascending order of length
	 * @throws IllegalArgumentException if
	 *                                  {@code words.length != Dabble.NUMBER_OF_WORDS}
	 *                                  or if the strings in word are not in
	 *                                  ascending order by length
	 */
	public Dabble(String... words) {
		if (words.length != Dabble.NUMBER_OF_WORDS) {
	        throw new IllegalArgumentException("expected 5 words, got " + Arrays.toString(words));
	    }
		
		this.solution = new TreeMap<>();
		this.scrambled = new TreeMap<>();
		
		for (int i = Dabble.MIN_WORD_LENGTH; i <= Dabble.MAX_WORD_LENGTH; i++) {
			int index = i - Dabble.MIN_WORD_LENGTH;
			if (words[index].length() != i) {
				throw new IllegalArgumentException("expected length of word: " + i + ", got: " + words[index].length());
			}
			
			this.solution.put(i, words[index]);
		}
		
		this.scrambled = ScrambleWords(this.solution);
	}

	/**
	 * Returns a string representation of the puzzle.
	 * 
	 * <p>
	 * The returned string consists of each scrambled word separated by a comma and
	 * space, followed by space-colon-space, followed by each solution word
	 * separated by a comma and space.
	 * 
	 * @return a string representation of the puzzle
	 */
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder(this.scrambled.get(Dabble.MIN_WORD_LENGTH));
		for (int len = Dabble.MIN_WORD_LENGTH + 1; len <= Dabble.MAX_WORD_LENGTH; len++) {
			b.append(", ");
			b.append(this.scrambled.get(len));
		}
		b.append(" : ");
		b.append(this.solution.get(Dabble.MIN_WORD_LENGTH));
		for (int len = Dabble.MIN_WORD_LENGTH + 1; len <= Dabble.MAX_WORD_LENGTH; len++) {
			b.append(", ");
			b.append(this.solution.get(len));
		}
		return b.toString();
	}
	
	/**
	 * Returns {@code true} if each scrambled word is contained in the dictionary
	 * used by the class, {@code false} otherwise.
	 * 
	 * <p>
	 * It is not the case that the scrambled words must be equal to the solution
	 * words because it is possible that many different solutions exist for any
	 * given puzzle.
	 * 
	 * @return {@code true} if each scrambled word is contained in the dictionary
	 *         used by the class, {@code false} otherwise.
	 */
	public boolean isSolved() {
		
		boolean result = true;
		
		Set<Integer> keys = this.scrambled.keySet();
		for (Integer key: keys) {
		    if (!DICT.contains(this.scrambled.get(key))) {
		    	result = false;
		    }
		}
		
		return result;
	}
	
	/**
	 * Exchange a letter in one scrambled word with a letter from a second scrambled
	 * word. The two scrambled words might be the same word, in which case two
	 * letters are exchanged in the same word.
	 * 
	 * <p>
	 * The letter having {@code index1} in the scrambled word having length
	 * {@code len1} is exchanged with the letter having {@code index2} in the
	 * scrambled word having length {@code len2}.
	 * 
	 * <p>
	 * Consider the dabble {@code d} whose string representation is equal to:
	 * 
	 * <p>
	 * {@code "eb, ueu, eyoh, rnhti, rrtacb : it, you, here, batch, burner"}
	 * 
	 * <p>
	 * Then {@code d.exchange(2, 0, 5, 4)} would exchange the first letter of
	 * {@code "eb"} with the last letter of {@code "rnhti"}, and
	 * {@code d.toString()} would return the string equal to:
	 * 
	 * <p>
	 * {@code "ib, ueu, eyoh, rnhtb, rrtacb : it, you, here, batch, burner"}
	 * 
	 * @param len1   the length of the first word
	 * @param index1 the index of the letter to exchange of the first word
	 * @param len2   the length of the second word
	 * @param index2 the index of the letter to exchange of the second word
	 * @throws IllegalArgumentException if {@code len1} or {@code len2} are not
	 *                                  valid Dabble word lengths, or if
	 *                                  {@code index1} or {@code index2} are not
	 *                                  valid indexes for their respective strings
	 */
	public void exchange(int len1, int index1, int len2, int index2) {
		if (len1 < Dabble.MIN_WORD_LENGTH || len1 > Dabble.MAX_WORD_LENGTH) {
			throw new IllegalArgumentException("exchange failed, len1 out of bound");
		}
		if (len2 < Dabble.MIN_WORD_LENGTH || len2 > Dabble.MAX_WORD_LENGTH) {
			throw new IllegalArgumentException("exchange failed, len2 out of bound");
		}
		if (index1 >= len1 || index1 < 0) {
			throw new IllegalArgumentException("exchange failed, index1 out of bound");
		}
		if (index2 >= len1 || index2 < 0) {
			throw new IllegalArgumentException("exchange failed, index2 out of bound");
		}
		
		char c1 = this.scrambled.get(len1).charAt(index1);
		char c2 = this.scrambled.get(len2).charAt(index2);
		
		StringBuilder word1 = new StringBuilder(this.scrambled.get(len1));
		word1.setCharAt(index1, c2);
		this.scrambled.put(len1, word1.toString());
		
		StringBuilder word2 = new StringBuilder(this.scrambled.get(len2));
		word2.setCharAt(index2, c1);
		this.scrambled.put(len2, word2.toString());
		
	}

	/**
	 * Returns the map of scrambled words.
	 * 
	 * <p>
	 * The returned map maps the word length to a scrambled word.
	 * 
	 * @return the map of scrambled words
	 */
	public Map<Integer, String> getScrambledWords() {
		// ALREADY DONE FOR YOU
		return this.scrambled;
	}

	/**
	 * Returns a map of solution words. More than one solution may exist; this
	 * method always returns the solution that was used to generate the puzzle.
	 * 
	 * <p>
	 * The returned map maps the word length to a solution word.
	 * 
	 * @return the map of solution words
	 */
	public Map<Integer, String> getSolutionWords() {
		// ALREADY DONE FOR YOU
		return this.solution;
	}
	
	/**
	 * @param	len the desired length of the random word
	 * @return	A random word from the dictionary of length len.
	 */
	private String FindRandomWord(int len) {

		List<String> t = Dabble.DICT.getWordsByLength(len);
		
		Random rng = new Random();
		int index = rng.nextInt(t.size());
		
		return t.get(index);
	}
	
	private Map<Integer, String> ScrambleWords(Map<Integer, String> startingWords) {
		
		Set<Integer> keys = startingWords.keySet();
		Collection<String> vals = startingWords.values();
		
		List<Character> c = new ArrayList<Character>();
		for (String s: vals) {
			for (int i = 0; i < s.length(); i++) {
				c.add(s.charAt(i));
			}
		}
		
		Map<Integer, String> result = new TreeMap<Integer, String>();
		Random rng = new Random();
		for (int key: keys) {
			
			String value = "";
			for(int i = 0; i < key; i++) {
				int index = rng.nextInt(c.size());
				value += c.get(index);
				c.remove(index);
			}
			result.put(key, value);
		}
		
		return result;
	}

	public static void main(String[] args) {
		Dabble dab = new Dabble(0);
		System.out.println(dab);
		
		System.out.println("isSolved(): " + dab.isSolved());

		System.out.println("Exchange(2, 1, 4, 3)");
		dab.exchange(2, 1, 4, 3);
		System.out.println(dab);
		
		System.out.println("Exchange(3, 2, 3, 2)");
		dab.exchange(3, 2, 3, 2);
		System.out.println(dab);
		
		System.out.println("Exchange(3, 0, 3, 2)");
		dab.exchange(3, 0, 3, 2);
		System.out.println(dab);
		
		System.out.println("Exchange(3, 0, 3, 2)");
		dab.exchange(3, 0, 3, 2);
		System.out.println(dab);
		
		System.out.println("Exchange(3, 0, 3, 2)");
		dab.exchange(3, 0, 3, 2);
		System.out.println(dab);
		
	}
}
