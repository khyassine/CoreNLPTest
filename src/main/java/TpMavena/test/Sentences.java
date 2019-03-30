package TpMavena.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class Sentences {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
			String text=File.readFile();
			
			Properties properties = new Properties();
		    properties.setProperty("annotators", "tokenize, ssplit, pos,lemma,ner");
		    properties.setProperty("properties", "StanfordCoreNLP-french.properties"); 
		    properties.setProperty("parse.model","edu/stanford/nlp/models/lexparser/frenchFactored.ser.gz"); 
		    properties.setProperty("pos.model","edu/stanford/nlp/models/pos-tagger/french/french.tagger");
		    properties.setProperty("ner.model","eunews.fr.crf.gz");
		    properties.setProperty("ner.useSUTime", "false");
		    properties.put("ner.applyFineGrained", "0");

		    StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);   
		    Annotation exampleAnnotation = new Annotation(text);
	        pipeline.annotate(exampleAnnotation);
	        FileOutputStream os = new FileOutputStream("nlp.xml");
	        pipeline.xmlPrint(exampleAnnotation, os);
	      
	        
	        List<CoreMap> sentences = exampleAnnotation.get(CoreAnnotations.SentencesAnnotation.class);

		    System.out.println("***********************  Phrases :  ***********************");
		    
		    for(CoreMap sentence : sentences) {
	        	System.out.println(sentence);
	        	
	        }
		    
		    System.out.println("*********************** Mots :  ***********************");

	        for(CoreMap sentence : sentences) {
	        	//System.out.println(sentence.toString());
	        	for(CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
	        		System.out.println("Token  : "+token.word()+"\t\t  | POS : "+token.get(PartOfSpeechAnnotation.class)+"     | NER : "+token.get(NamedEntityTagAnnotation.class));
	        	}
	        }

	}

}
