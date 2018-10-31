package calc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Calculator
{

    //Check for to see if string is number
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

    //cleans up the users input removing unneccessary characters
    //it then splits on spaces in preperation for sorting into RPN
    public static String[] cleanInput(String input)
    {
        String output = input;

        output = output.replaceAll("\\-{2,}", "\\- \\-");

        output = output.replaceAll("(?<ops>[+*/^()])", " ${ops} ");

        output = output.replaceAll("\\s+", " ").trim();

        return output.split(" ");
    }

    //puts the input into RPN and seperates the operands and numbers
    //creates a stack of operations and a queue to store the current solution
    //after moving through each item in the text box it will place the
    //operands in the queue in order of pirority in preperation for calculation
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

    //calculates the now properlly formated equation,
    //works through the queue and applies the operands to the numbers in correct
    //order. returns the solution in form of a string
    public static String solve(Queue<String> input)
    {
        Stack<Double> solution = new Stack<>();

        double temp1;
        double temp2;

        while (!input.isEmpty())
        {
            String value = input.remove();

            if (isNumber(value))
            {
                solution.push(Double.valueOf(value));
            } else
            {

                switch (value)
                {
                    case "+":

                        if (solution.size() >= 2)
                        {
                            temp2 = solution.pop();
                            temp1 = solution.pop();
                            solution.push(temp1 + temp2);
                        }
                        break;
                    case "-":
                        if (solution.size() >= 2)
                        {
                            temp2 = solution.pop();
                            temp1 = solution.pop();
                            solution.push(temp1 - temp2);
                        }
                        break;
                    case "*":
                        if (solution.size() >= 2)
                        {
                            temp2 = solution.pop();
                            temp1 = solution.pop();
                            solution.push(temp1 * temp2);
                        }
                        break;
                    case "/":
                        if (solution.size() >= 2)
                        {
                            temp2 = solution.pop();
                            temp1 = solution.pop();
                            solution.push(temp1 / temp2);
                        }
                        break;
                    case "^":
                        if (solution.size() >= 2)
                        {
                            temp2 = solution.pop();
                            temp1 = solution.pop();
                            solution.push(Math.pow(temp1, temp2));
                        }
                }
            }

        }

        if (solution.size() == 1)
        {
            return Double.toString(solution.pop());
        }

        return "Syntax error";
    }

    //this method is directly called from the calc class when equals is pressed
    //the method calls the above methods in correct order in order to 
    //calculate the equaton in the text box
    //returns string to be printed in text box on calculator
    public static String equals(String input)
    {
        return solve(rPN(cleanInput(input)));
    }
}
