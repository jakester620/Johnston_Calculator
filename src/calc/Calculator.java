package calc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Calculator
{

    static boolean isNumber(String str)
    {
        try
        {
            Double.valueOf(str);
            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }

    public static String[] cleanInput(String input)
    {
        String output = input;

        output = output.replaceAll("\\-{2,}", "\\- \\-");

        output = output.replaceAll("(?<ops>[+*/^()])", " ${ops} ");

        output = output.replaceAll("\\s+", " ").trim();

        return output.split(" ");
    }

    public static Queue<String> rPN(String[] sep)
    {
        Map<String, Integer> prio = new HashMap<>();
        prio.put("^", 4);
        prio.put("*", 3);
        prio.put("/", 3);
        prio.put("-", 2);
        prio.put("+", 2);
        prio.put("(", 1);

        Stack<String> ops = new Stack<>();

        Queue<String> solution = new LinkedList<>();

        for (String value : sep)
        {
            if (value.equals("("))
            {
                ops.push(value);
                continue;
            }
            if (value.equals(")"))
            {
                while (!"(".equals(ops.peek()))
                {
                    solution.add(ops.pop());
                }
                ops.pop();
                continue;
            }
            if (prio.containsKey(value))
            {
                while (!ops.empty() && prio.get(value) <= prio.get(ops.peek()))
                {
                    solution.add(ops.pop());
                }
                ops.push(value);
                continue;
            }

            if (isNumber(value))
            {
                solution.add(value);
                continue;
            }

            throw new IllegalArgumentException("Error!");
        }

        while (!ops.isEmpty())
        {
            solution.add(ops.pop());
        }
        return solution;
    }

    public static double solve(Queue<String> input)
    {
        Stack<Double> sol = new Stack<>();

        double var1;
        double var2;

        while (!input.isEmpty())
        {
            String value = input.remove();

            if (isNumber(value))
            {
                sol.push(Double.valueOf(value));
            } else
            {

                switch (value)
                {
                    case "+":

                        if (sol.size() >= 2)
                        {
                            var2 = sol.pop();
                            var1 = sol.pop();
                            sol.push(var1 + var2);
                        } else
                        {
                            throw new IllegalArgumentException("Error!");
                        }
                        break;
                    case "-":
                        if (sol.size() >= 2)
                        {
                            var2 = sol.pop();
                            var1 = sol.pop();
                            sol.push(var1 - var2);
                        } else
                        {
                            throw new IllegalArgumentException("Error!");
                        }
                        break;
                    case "*":
                        if (sol.size() >= 2)
                        {
                            var2 = sol.pop();
                            var1 = sol.pop();
                            sol.push(var1 * var2);
                        } else
                        {
                            throw new IllegalArgumentException("Error!");
                        }
                        break;
                    case "/":
                        if (sol.size() >= 2)
                        {
                            var2 = sol.pop();
                            var1 = sol.pop();
                            sol.push(var1 / var2);
                        } else
                        {
                            throw new IllegalArgumentException("Error!");
                        }
                        break;
                    case "^":
                        if (sol.size() >= 2)
                        {
                            var2 = sol.pop();
                            var1 = sol.pop();
                            sol.push(Math.pow(var1, var2));
                        } else
                        {
                            throw new IllegalArgumentException("Error!");
                        }
                }
            }

        }

        if (sol.size() == 1)
        {
            return sol.pop();
        }

        return -1;
    }

    public static String equals(String input)
    {
        String[] formated;
        Queue organized;
        formated = cleanInput(input);
        organized = rPN(formated);
        double calculated = solve(organized);
        return Double.toString(calculated);
    }

    /**
     *
     * @param str
     * @return
     */
    public static Double calculate(String str)
    {
        return solve(rPN(cleanInput(str)));
    }
}
