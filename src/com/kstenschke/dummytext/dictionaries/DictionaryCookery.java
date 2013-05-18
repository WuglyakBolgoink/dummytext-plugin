/*
 * Copyright 2013 Kay Stenschke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kstenschke.dummytext.dictionaries;

import com.kstenschke.dummytext.TextualHelper;

public class DictionaryCookery extends Dictionary {

	/**
	 * Constructor
	 */
	public DictionaryCookery() {

	}

	/**
	 * @param   amountWords
	 * @param   amountSentences
	 * @return  One line of random text, consisting from the given amount of words and sentences
	 */
	public String getRandomLine(Integer amountWords, Integer amountSentences) {
		String sentence   = "";

		for(Integer i =0; i< amountSentences; i++) {
			sentence = sentence.concat( (i > 0 ? " " : "") + getSentenceStructure(amountWords) );
		}

		while( sentence.matches(".*[0-9].*") ) {
			while(sentence.contains("1")) sentence = replaceIfNew(sentence, "1", getIngredient() );
			while(sentence.contains("2")) sentence = replaceIfNew(sentence, "2", getFluid() );
			while(sentence.contains("3")) sentence = replaceIfNew(sentence, "3", getVerbTransitive() );
			while(sentence.contains("4")) sentence = replaceIfNew(sentence, "4", getVerbIntransitive() );
			while(sentence.contains("5")) sentence = replaceIfNew(sentence, "5", getAdjective() );
			while(sentence.contains("6")) sentence = replaceIfNew(sentence, "6", getAdverb() );
			while(sentence.contains("7")) sentence = replaceIfNew(sentence, "7", getSeasonings() );
			while(sentence.contains("8")) sentence = replaceIfNew(sentence, "8", getContainer() );
			while(sentence.contains("9")) sentence = replaceIfNew(sentence, "9", getAmount() );
			while(sentence.contains("RNDNUM")) sentence = sentence.replaceFirst("RNDNUM", getNumber() );
			while(sentence.contains("HEATUP")) sentence = sentence.replaceFirst("HEATUP", getVerbHeatUp() );
		}

		sentence = fixIndefiniteArticles(sentence);

		sentence = sentence.replaceAll("breaked",       "broken");
		sentence = sentence.replaceAll("choped",        "chopped");
		sentence = sentence.replaceAll("choping",       "chopping");
		sentence = sentence.replaceAll("chickens",      "chicken");
		sentence = sentence.replaceAll("sautéed",       "seared");
		sentence = sentence.replaceAll("shreding",      "shredding");
		sentence = sentence.replaceAll("sliceing",      "slicing");
		sentence = sentence.replaceAll("sliceed",       "sliced");
		sentence = sentence.replaceAll("spinachs",      "spinach");
		sentence = sentence.replaceAll("sauce soup",    "soup");

		String[] unincreasables   = {
			"beefs", "berries", "breasts", "carrots", "chicken", "chicken", "chickpeas", "crumps", "eggs", "meatballs", "nachos", "noodles",
			"oysters", "oysters", "peanuts", "peas", "pickles", "pickles", "rice", "s taste", "sauerkraut", "seeds", "seeds",
			"shrimps", "spinach", "truffles", "turkey"
		};
		sentence = TextualHelper.depluralize(sentence, unincreasables);

		return ucfirst(sentence);
	}

	/**
	 * @param   amountWords
	 * @return  Random sentence structure with numbers as word type placeholders
	 */
	private static String getSentenceStructure(Integer amountWords) {
		if( amountWords != null && amountWords == 1 ) {
			String[] structures  = {"1", "2", "8"};
			return pickRandomString(structures);
		}

		String[] structures  = {
			"4 RNDNUM 1s, 1, and 7 in a large 8 over medium heat, HEATUP for RNDNUM minutes and 3 some 1.",
			"High-tech 1s. Mysterious 2. Super1. Are we supposed to eat this stuff? Or is it going to eat us?",
			"combine 1, 1 and 1. 3 with 5 7 and serve 4ed with 1. Enjoy!",
			"2 soup is just not the same without 7 and 5 5 1s.",
			"per guest prepare 9 of 2 with 4ed 1 for dessert.",
			"4 1 6, then mix with 2 and serve 6 in 8.",
			"when 4ing 1s, 3 1, 1 and 2 with it in a 8.",
			"all children like 4ed 1s in 2 and 7.",
			"1 tastes best with 2 and lots of 7.",
			"5, 5 pudding is best 3ed with 5 2.",
			"3 each side of the 1 with 9 of 1.",
			"To the 5 1 add 1, 1, 2 and 5 1.",
			"3 9 of 1 in 9 of 2.",
			"with 1s drink 2."
		};

		return pickRandomString(structures, amountWords);
	}

	/**
	 * @return  Word of group 1: ingredients
	 */
	private static String getIngredient() {
		String[] words ={
			"asparagus", "avocado", "bagel", "blueberries", "bok choy", "broccoli", "butter", "cabbage", "carrots", "caviar",
			"celery", "chicken breasts", "chicken lard", "chicken", "chickpeas", "chickpeas", "chicory", "chili", "chocolate",
			"cracker crumps", "cucumber", "doughnut", "eggs", "escargot", "garlic", "ginger", "ground beef", "herring",
			"leek", "lettuce", "lobster", "marshmellow", "meatballs", "meatloaf", "melon", "nachos", "noodles", "okra",
			"onion", "oysters", "peanut butter", "peanuts", "pickles", "popcorn", "pork butt", "pork shoulder",
			"pumpkin seeds", "ramen", "raspberries", "rhubarb", "rice", "sauerkraut", "shrimps", "spinach", "squid",
			"steak", "strawberries", "tofu", "truffels", "tuna", "turkey", "watermelon", "white bread", "zucchini"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 2:  fluid
	 */
	private static String getFluid() {
		String[] words ={
			"BBQ sauce", "adobo sauce", "anchovy essence", "beer", "bourbon", "buttermilk", "champaign", "cocktail sauce",
			"condensed milk", "cream", "crême fraîche", "emeril\'s essence", "fish sauce", "gold tequila", "gravy",
			"hollandaise sauce", "honey", "joghurt", "kefir", "ketchup", "lemon juice", "mayonnaise", "milk", "mint sauce",
			"olive oil", "orange juice", "oyster sauce", "peanut sauce", "peppermint tea", "plain vinegar", "red wine",
			"remoulade", "rice vinegar", "ricotta", "rum", "salad cream", "salsa verde", "sour milk", "soy sauce",
			"sweet chili sauce", "tabasco", "triple sec", "vinaigrette", "vinegar", "water", "white wine",
			"worcestershire sauce"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 3:  transitive verb
	 */
	private static String getVerbTransitive() {
		String[] words = {
			"cover", "garnish", "mix", "season", "stir in"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 4:  intransitive verb
	 */
	private static String getVerbIntransitive() {
		String[] words = {
			"break", "chop", "cook", "crush", "cut", "drain", "grill", "heat", "roast", "shred", "slice", "smash", "simmer",
			"warm", "combine"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group "HEATUP"
	 */
	private static String getVerbHeatUp() {
		String[] words = {
			"cook", "grill", "heat", "roast", "simmer", "warm", "sauté"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 5:  adjective
	 */
	private static String getAdjective() {
		String[] words = {
			"aged", "aromatic", "bloody", "chopped", "chilled", "clammy", "cold", "cored", "crushed", "dark", "delicious", "divided", "dried", "fresh", "gooey",
			"grey", "ground", "hardened", "heated", "hot", "iced", "juicy", "large", "large", "melted", "mild", "minced", "muddy", "niffy",
			"nutty", "old", "packaged", "quartered", "raw", "ripe", "roasted", "salted", "salty", "shredded", "sliced", "slobbery", "small",
			"smashed", "smooth", "soaked", "sour", "springy", "squeezed", "sticky", "sun-dried", "sweet", "synthetic",
			"sour", "tangy", "tasty", "tender", "warm", "yellow"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 6:  adverb
	 */
	private static String getAdverb() {
		String[] words = {
			"carefully", "fast", "freshly", "smoothly", "regularly 5", "carefully 5", "drizzled with 2"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 7:  seasonings
	 */
	private static String getSeasonings() {
		String[] words ={
			"anise", "baking powder", "basil leafs", "basil", "black cardamon", "black cardamon", "black pepper",
			"brown sugar", "butter", "cayenne pepper", "celery", "chocolate", "cinnamon", "corn syrup", "cumin", "curry", "dill", "dill",
			"flower", "garlic", "green curry", "jasmine", "lime", "marmalade", "mustard", "nutmeg", "onion powder",
			"oregano", "parsley", "pepper", "radish sprouts", "rosemary", "rum", "sugar", "szechuan pepper", "thyme",
			"vegemite", "vodka", "wasabi", "woodruff", "za\'atar"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 8: abstract place (usable w/o article: "going to 8")
	 */
	private static String getContainer() {
		String[] words ={
			"bowl", "casserole", "cooker", "frying pan", "grinder", "ice blender", "pan", "saucepan", "sauté pan",
			"soup pot", "wok"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 9: ingredients
	 */
	private static String getAmount() {
		String[] words ={
			"RNDNUM peaces", "RNDNUM pounds", "RNDNUM teaspoons", "half a kilo", "one container", "one cup", "one jar",
			"one package", "one quarter cup", "RNDNUM oz", "RNDNUM pounds", "RNDNUM tablespoons"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  A number word
	 */
	private static String getNumber() {
		String[] words ={
			"two", "three", "four", "five", "six", "seven", "eight", "nine", "eleven", "twelve", "fifteen", "twenty",
			"a dozen", "a handfull"
		};

		return pickRandomString(words);
	}

}