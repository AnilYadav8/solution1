import java.util.*;

public class solution {
    static Map<String, List<List<String>>> recipes = new HashMap<>();
    static Map<String, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] parts = line.split("=");
            String potion = parts[0];
            String[] ingredients = parts[1].split("\\+");

            recipes.putIfAbsent(potion, new ArrayList<>());
            recipes.get(potion).add(Arrays.asList(ingredients));
        }

        String target = sc.nextLine().trim();
        System.out.println(cost(target));
        sc.close();
    }

    
    static int cost(String potion) {
        
        if (memo.containsKey(potion)) {
            return memo.get(potion);
        }

        
        if (!recipes.containsKey(potion)) {
            return 0;
        }

        int best = Integer.MAX_VALUE;

        
        for (List<String> ingList : recipes.get(potion)) {
            int recipeCost = 0;
            for (String ing : ingList) {
                recipeCost += cost(ing);
            }
            recipeCost += (ingList.size() - 1); // combining cost
            best = Math.min(best, recipeCost);
        }

        memo.put(potion, best);
        return best;
    }
}
