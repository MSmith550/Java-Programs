package codoncounter;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Matt
 */
public class CodonCounter {

    public static void main(String[] args) {
        String dnaStrand = "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC";
        Map<Integer, Map<String, Integer>> frameCodonCounts = countCodonsByFrame(dnaStrand);

        // Print codon counts for each frame
        for (Map.Entry<Integer, Map<String, Integer>> frameEntry : frameCodonCounts.entrySet()) {
            int frame = frameEntry.getKey();
            int total = 0;
            System.out.println("Frame " + frame + " counts:");
            Map<String, Integer> codonCounts = frameEntry.getValue();
            for (Map.Entry<String, Integer> entry : codonCounts.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
                total += entry.getValue();
            }
            System.out.println(total);
        }
    }

    private static Map<Integer, Map<String, Integer>> countCodonsByFrame(String dnaStrand) {
        Map<Integer, Map<String, Integer>> frameCodonCounts = new HashMap<>();

        // Iterate over each reading frame
        for (int frame = 0; frame < 3; frame++) {
            Map<String, Integer> codonCounts = new HashMap<>();
            for (int i = frame; i <= dnaStrand.length() - 3; i += 3) {
                String codon = dnaStrand.substring(i, i + 3);
                codonCounts.put(codon, codonCounts.getOrDefault(codon, 0) + 1);
            }
            frameCodonCounts.put(frame, codonCounts);
        }

        return frameCodonCounts;
    }
    
    private static String getMostCommonCodon(Map<String, Integer> codonCounts) {
        String mostCommonCodon = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : codonCounts.entrySet()) {
            String codon = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                maxCount = count;
                mostCommonCodon = codon;
            }
        }

        return mostCommonCodon;
    }
}
