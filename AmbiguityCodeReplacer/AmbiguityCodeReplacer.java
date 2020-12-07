import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AmbiguityCodeReplacer {
    private HashMap<String, List<String>> ambiguityCodeMatches;

    public AmbiguityCodeReplacer(){
        initAmbiguityCodeMatches();
    }

    public List<String> replaceAmbiguityCodes(String sequence, Boolean singleAlternative) {
        List<String> ambiguityCodesReplacedSeqList = new ArrayList<String>();
        ambiguityCodesReplacedSeqList.add(sequence);

        while(containsAmbiguityCode(ambiguityCodesReplacedSeqList.get(0))){
            String firstSeqInTheList = ambiguityCodesReplacedSeqList.get(0);

            int firstLocationOfAmbCode = findFirstLocationOfAmbCode(firstSeqInTheList);

            String codeToBeReplaced = String.valueOf(firstSeqInTheList.charAt(firstLocationOfAmbCode));

            List<String> ambiguityCodeReplacers = ambiguityCodeMatches.get(codeToBeReplaced);
            List<String> tmpList = new ArrayList<String>();

            if (singleAlternative){
                for (String seq: ambiguityCodesReplacedSeqList) {
                    tmpList.add(seq.replaceFirst(codeToBeReplaced, ambiguityCodeReplacers.get(0)));
                }
            } else if(ambiguityCodesReplacedSeqList.size() > 3000) {
                for (String seq: ambiguityCodesReplacedSeqList) {
                    tmpList.add(seq.replaceFirst(codeToBeReplaced, ambiguityCodeReplacers.get(0)));
                }
            } else {
                for (String replacer: ambiguityCodeReplacers) {
                    for (String seq: ambiguityCodesReplacedSeqList) {
                        tmpList.add(seq.replaceFirst(codeToBeReplaced, replacer));
                    }
                }
            }

            ambiguityCodesReplacedSeqList.clear();
            ambiguityCodesReplacedSeqList.addAll(tmpList);
        }

        return ambiguityCodesReplacedSeqList;
    }

    private int findFirstLocationOfAmbCode(String sequence) {
        for(int i = 0; i < sequence.length(); i++){
            if(!(sequence.charAt(i) == 'A'
                || sequence.charAt(i) == 'C'
                || sequence.charAt(i) == 'G'
                || sequence.charAt(i) == 'T')){
                return i;
            }
        }
        return -1;
    }

    private boolean containsAmbiguityCode(String sequence){
        for(int i = 0; i < sequence.length(); i++){
            if(!(sequence.charAt(i) == 'A'
                || sequence.charAt(i) == 'C'
                || sequence.charAt(i) == 'G'
                || sequence.charAt(i) == 'T')){
                return true;
            }
        }
        return false;
    }

    private void initAmbiguityCodeMatches() {
        ambiguityCodeMatches = new HashMap<>();
        ambiguityCodeMatches.put("R", Arrays.asList("A", "G"));
        ambiguityCodeMatches.put("Y", Arrays.asList("C", "T"));
        ambiguityCodeMatches.put("S", Arrays.asList("G", "C"));
        ambiguityCodeMatches.put("W", Arrays.asList("A", "T"));
        ambiguityCodeMatches.put("K", Arrays.asList("G", "T"));
        ambiguityCodeMatches.put("M", Arrays.asList("A", "C"));
        ambiguityCodeMatches.put("B", Arrays.asList("C", "G", "T"));
        ambiguityCodeMatches.put("D", Arrays.asList("A", "G", "T"));
        ambiguityCodeMatches.put("H", Arrays.asList("A", "C", "T"));
        ambiguityCodeMatches.put("V", Arrays.asList("A", "C", "G"));
        ambiguityCodeMatches.put("N", Arrays.asList("A", "C", "G", "T"));

        ambiguityCodeMatches.put("r", Arrays.asList("A", "G"));
        ambiguityCodeMatches.put("y", Arrays.asList("C", "T"));
        ambiguityCodeMatches.put("s", Arrays.asList("G", "C"));
        ambiguityCodeMatches.put("w", Arrays.asList("A", "T"));
        ambiguityCodeMatches.put("k", Arrays.asList("G", "T"));
        ambiguityCodeMatches.put("m", Arrays.asList("A", "C"));
        ambiguityCodeMatches.put("b", Arrays.asList("C", "G", "T"));
        ambiguityCodeMatches.put("d", Arrays.asList("A", "G", "T"));
        ambiguityCodeMatches.put("h", Arrays.asList("A", "C", "T"));
        ambiguityCodeMatches.put("v", Arrays.asList("A", "C", "G"));
        ambiguityCodeMatches.put("n", Arrays.asList("A", "C", "G", "T"));
    }

    public static void main(String[] args){
        AmbiguityCodeReplacer ambiguityCodeReplacer = new AmbiguityCodeReplacer();
        List<String> ambiguityCodesReplacedStringList =  ambiguityCodeReplacer.replaceAmbiguityCodes("GNSGGGGASSGGGGCSDGGGSDGRGGGNGGGGAGGGGVGGGBR", false);

        try (PrintWriter writer = new PrintWriter("/Users/gulsah/RepeatMasker_Analysis/GNSGGGGASSGGGGCSDGGGSDGRGGGNGGGGAGGGGVGGGBR.txt", "UTF-8")) {
            int counter = 1;
            for(String str:ambiguityCodesReplacedStringList){
                writer.println(">" + counter);
                writer.println(str);
                counter++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
