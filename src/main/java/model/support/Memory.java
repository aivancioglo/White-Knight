package model.support;

import model.support.allEnums.Skill;
import model.unit.Unit;

import java.io.*;

public abstract class Memory {
    public static Unit save(Unit unit) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("db/" + unit.name() + ".txt")))) {
            writer.write(unit.name());
            writer.newLine();
            writer.write(String.valueOf(unit.concentration()) + ",");
            writer.write(String.valueOf(unit.strength()) + ",");
            writer.write(String.valueOf(unit.agility()));
            writer.newLine();
            writer.write(String.valueOf(unit.health()) + ",");
            writer.write(String.valueOf(unit.stamina()));
            writer.newLine();
            unit.skills().forEach((k, v) -> {
                try {
                    writer.write(String.valueOf(k) + "=" + String.valueOf(v) + ",");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return unit;
    }

    public static Unit load(Unit unit) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("db/" + unit.name() + ".txt")))) {
            reader.readLine();

            String[] data = reader.readLine().split(",");
            unit.addToConcentration(Integer.parseInt(data[0]) - 1);
            unit.addToStrength(Integer.parseInt(data[1]) - 1);
            unit.addToAgility(Integer.parseInt(data[2]) - 1);

            data = reader.readLine().split(",");
            unit.setHealth(Double.valueOf(data[0]));
            unit.setStamina(Double.valueOf(data[1]));

            data = reader.readLine().split(",");
            for (int i = 0; i < unit.skills().size(); i++) {
                unit.skills().put(Skill.valueOf(data[i].split("=")[0]).type, Double.valueOf(data[i].split("=")[1]));
            }
        } catch (IOException e) {
            System.out.println("Can't load " + unit.name() + ".txt. File doesn't exist!");;
        }

        return unit;
    }

    public static Unit delete(Unit unit) {
        new File("db/" + unit.name() + ".txt").delete();

        return unit;
    }
}
