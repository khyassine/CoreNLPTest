#!/bin/bash
#########################################################
#Author: Marie Dubremetz
#
#(File)->(file)
#		**Preriquisites**
#		You need to install and be able to use Tree-Tagger and stanford coreNLP
#		**Purpose:**
#		Given a xmlfile parsed by stanford, the script replace all the words under "<lemma>" by the lemmatized version
#
#		First run your stanford parser on your french text with output xml (with the annotator lemma, which will not do anything except from adding the "lemma" tag with the word in it.
#		For me the command was:
#		java -mx3g -cp "*" edu.stanford.nlp.pipeline.StanfordCoreNLP -props StanfordCoreNLP-french.properties -annotators tokenize,ssplit,pos,lemma,parse -file myfile -outputFormat xml
#		**Usage:**
#		$sh baseline.sh myfile.xml
#		old output:
		# <token id="1">
		# <word>les</word>
		# <lemma>les</lemma>
		# <CharacterOffsetBegin>0</CharacterOffsetBegin>
		# <CharacterOffsetEnd>7</CharacterOffsetEnd>
		# <POS>V</POS>
#		**Example of desired output:**
		# <token id="1">
		# <word>les</word>
		# <lemma>le</lemma>
		# <CharacterOffsetBegin>0</CharacterOffsetBegin>
		# <CharacterOffsetEnd>7</CharacterOffsetEnd>
		# <POS>V</POS>
#		
#
#########################################################

outputFile=$(basename $1 | cut -f 1 -d '.').lem.xml
echo "" > $outputFile
#in order to avoid the <unknown> add the option  tag -no-unknown inside the "tree-tagger-french-utf8" file in your TreeTagger folder
cat $1 | while read in; do if [[ "$in" == *"lemma"* ]] ; then echo -n "<lemma>" >> $outputFile & echo -n $in | cut -d ">" -f2 | cut -d "<" -f1 | tree-tagger-french | cut -f3 | awk '{printf $0}' >> $outputFile && echo "</lemma>" >> $outputFile; else echo $in >>$outputFile; fi;done; 
cat $outputFile | tr -d '\015' > temp
cat temp > $outputFile
rm temp
