import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Project {

    static Random random = new Random();

    static String[] names = {"Karabas", "Oglak", "Malak", "Giorgio", "Turkes", "Kabak", "Selen", "Viran", "Muddie", "Topg"};

    static class Creature {
        String name;
        int age;
        boolean alive;
        boolean fed;
        int food;

        public Creature() {
            this.name = names[random.nextInt(names.length)];
            this.age = 0;
            this.alive = true;
            this.fed = false;
            this.food = 0;
        }

        void checkFood() {
            if (food > 2) {
                fed = true;
                food -= 2;
            } else {
                fed = false;
                checkDeath();
            }
        }

        void checkDeath() {
            if (!alive) return;
            if (!fed || age > 5) {
                alive = false;
                System.out.println(name + " has died.");
            }
        }

        Creature reproduce() {
            if (alive && fed) {
                Creature child = new Creature();
                System.out.println(name + " has reproduced. New creature: " + child.name);
                return child;
            }
            return null;
        }
    }

    static void runSimulation(List<Creature> creatures, int steps) {
        for (int step = 0; step < steps; step++) {
            creatures.forEach(creature -> {
                creature.food += random.nextInt(3);
                creature.checkFood();
                if (random.nextInt(10) < 2) {
                    Creature child = creature.reproduce();
                    if (child != null) {
                        creatures.add(child);
                    }
                }
                creature.age++;
            });
        }
    }

    public static void main(String[] args) {
        List<Creature> creatures = new ArrayList<>();
        creatures.add(new Creature());

        int steps = 10;
        runSimulation(creatures, steps);
    }
}
