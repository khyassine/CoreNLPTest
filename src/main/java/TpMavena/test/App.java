package TpMavena.test;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import edu.stanford.nlp.util.CoreMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	
    	
    	
        String t="Paris est la capitale de la France. L’agglomération de Paris compte plus de 10 millions d’habitants. Un fleuve traverse la capitale française, c’est la Seine. Dans Paris, il y a deux îles :  l’île de la Cité et l’île Saint-Louis.\n" + 
        		"\n" + 
        		"Paris compte vingt arrondissements. Le 16e, le 7e et le 8e arrondissements de Paris sont les quartiers les plus riches. Ils sont situés dans l’ouest de la capitale. Les quartiers populaires comme le 19e et le 20e sont au nord-est de la ville. Les monuments célèbres, les ministères, le palais de l’Élysée sont situés dans le centre de Paris.\n" + 
        		"\n" + 
        		"Paris est la capitale économique, la capitale politique et la capitale culturelle de la France. La ville compte beaucoup de lieux célèbres dans le monde entier comme « la tour Eiffel » , « l’Arc de Triomphe » et « Notre-Dame de Paris ». Les musées parisiens aussi sont très connus. Il y a, par exemple, le musée du Louvre. C’est le plus grand musée de France. On peut voir dans le musée du Louvre des tableaux magnifiques. Le plus célèbre est certainement « La Joconde » de Léonard de Vinci.\n" + 
        		"\n" + 
        		"Paris est une ville très touristique. Chaque année, des millions de touristes du monde entier marchent sur les Champs-Élysées. Ils séjournent à l’hôtel, louent des chambres d’hôtes ou des appartements pour une semaine.\n" + 
        		"\n" + 
        		"";
      
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit");
        props.setProperty("tokenize.language", "fr");
        // set up pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // the following has examples for the new Core Wrapper API and the older Annotation API
        // example using Core Wrappers (new API designed to make it easier to work with NLP data)
        System.out.println("---");
        System.out.println("Accessing Tokens In A CoreDocument");
        System.out.println("(text, char offset begin, char offset end)");
        CoreDocument exampleDocument = new CoreDocument(t);
        // annotate document
        pipeline.annotate(exampleDocument);
        // access tokens from a CoreDocument
        // a token is represented by a CoreLabel
        List<CoreLabel> firstSentenceTokens = exampleDocument.sentences().get(0).tokens();
        // this for loop will print out all of the tokens and the character offset info
        for (CoreLabel token : firstSentenceTokens) {
          System.out.println(token.word() + "\t" + token.beginPosition() + "\t" + token.endPosition());
        }
        // example using older Annotation API
        System.out.println("---");
        System.out.println("Accessing Tokens In An Annotation");
        System.out.println("(text, char offset begin, char offset end)");
        Annotation exampleAnnotation = new Annotation(t);
        pipeline.annotate(exampleAnnotation);
        
        // this for loop will print out all of the tokens and the character offset info
        for(int i=0;i<=exampleAnnotation.size();i++) {
        	
            CoreMap firstSentence = exampleAnnotation.get(CoreAnnotations.SentencesAnnotation.class).get(i);

        for (CoreLabel token : firstSentence.get(CoreAnnotations.TokensAnnotation.class)) {
          System.out.println(token.word() + "\t" + token.beginPosition() + "\t" + token.endPosition());
        }
        
        }
      }

}
