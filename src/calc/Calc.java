package calc;

//import all required
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Queue;

//created main calc class, extending jframe for creation of gui
public class Calc extends JFrame
{

    //textfield for displaying output
    private JTextField numField;

    //main jpanel
    private JPanel field,
            numbers,
            all;

    //declaration of all jbuttons
    private JButton clear, posNeg, leftp, rightp, devide,
            seven, eight, nine, mult,
            four, five, six, sub,
            one, two, three, add,
            zero, point, power, equals, delete;

    //sizing for the main window
    private final int WINDOW_WIDTH = 500,
            WINDOW_HEIGHT = 500;

    //calc constructor
    public Calc()
    {
        setTitle("CALCULATOR");
        buildPanel();
        add(all);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //builds the calculator gui
    private void buildPanel()
    {
        numField = new JTextField(10);

        Font font1 = new Font("SansSerif", Font.BOLD, 40);
        numField.setHorizontalAlignment(JTextField.LEFT);
        numField.setFont(font1);
        numField.setEditable(false);

        zero = new JButton(" 0 ");
        zero.addActionListener(new zeroButtonListener());

        one = new JButton(" 1 ");
        one.addActionListener(new oneButtonListener());

        two = new JButton(" 2 ");
        two.addActionListener(new twoButtonListener());

        three = new JButton(" 3 ");
        three.addActionListener(new threeButtonListener());

        four = new JButton(" 4 ");
        four.addActionListener(new fourButtonListener());

        five = new JButton(" 5 ");
        five.addActionListener(new fiveButtonListener());

        six = new JButton(" 6 ");
        six.addActionListener(new sixButtonListener());

        seven = new JButton(" 7 ");
        seven.addActionListener(new sevenButtonListener());

        eight = new JButton(" 8 ");
        eight.addActionListener(new eightButtonListener());

        nine = new JButton(" 9 ");
        nine.addActionListener(new nineButtonListener());

        add = new JButton(" + ");
        add.addActionListener(new addButtonListener());

        sub = new JButton(" - ");
        sub.addActionListener(new subButtonListener());

        mult = new JButton(" x ");
        mult.addActionListener(new multButtonListener());

        devide = new JButton(" / ");
        devide.addActionListener(new devideButtonListener());

        clear = new JButton(" C ");
        clear.addActionListener(new clearButtonListener());

        posNeg = new JButton(" NEG ");
        posNeg.addActionListener(new posNegButtonListener());

        equals = new JButton(" = ");
        equals.addActionListener(new equalsButtonListener());

        point = new JButton(" . ");
        point.addActionListener(new pointButtonListener());

        leftp = new JButton(" ( ");
        leftp.addActionListener(new leftpButtonListener());

        rightp = new JButton(" ) ");
        rightp.addActionListener(new rightpButtonListener());

        power = new JButton("^");
        power.addActionListener(new powerButtonListener());

        delete = new JButton("DEL");
        delete.addActionListener(new deleteButtonListener());

        field = new JPanel();
        field.add(numField);

        numbers = new JPanel();
        numbers.setLayout(new GridLayout(4, 4));
        numbers.add(seven);
        numbers.add(eight);
        numbers.add(nine);
        numbers.add(power);
        numbers.add(devide);
        numbers.add(clear);
        numbers.add(four);
        numbers.add(five);
        numbers.add(six);
        numbers.add(mult);
        numbers.add(sub);
        numbers.add(add);
        numbers.add(one);
        numbers.add(two);
        numbers.add(three);
        numbers.add(leftp);
        numbers.add(rightp);
        numbers.add(equals);
        numbers.add(posNeg);
        numbers.add(zero);
        numbers.add(point);
        numbers.add(delete);

        all = new JPanel();
        all.setLayout(new BorderLayout());
        all.add(field, BorderLayout.NORTH);
        all.add(numbers, BorderLayout.CENTER);
    }

    //equals button listener, calls equals method in calculator
    //this then completed the formating and calculations
    private class equalsButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String input = numField.getText();
                String solution = Calculator.equals(input);
                numField.setText(solution);

            } catch (Exception ex)
            {
                numField.setText("Syntax error");
            }
        }
    }

    //adds left parenthasis to the text box
    private class leftpButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            numField.setText(numField.getText() + "(");
        }
    }

    //adds right parenthasis to the text box
    private class rightpButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            numField.setText(numField.getText() + ")");
        }
    }

    //adds power symbol to text box
    private class powerButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText(numField.getText());
            } else
            {
                numField.setText(numField.getText() + " ^ ");
            }
        }
    }

    //clears text box
    private class clearButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            numField.setText("");
        }
    }

    //adds sub sign to text box
    private class subButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText(numField.getText());
            } else
            {
                numField.setText(numField.getText() + " - ");
            }
        }
    }

    //adds plus sign to text box
    private class addButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText(numField.getText());
            } else
            {
                numField.setText(numField.getText() + " + ");
            }
        }
    }

    //adds devide sigh to text box
    private class devideButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText(numField.getText());
            } else
            {
                numField.setText(numField.getText() + " / ");
            }
        }
    }

    //adds multiply button to text box
    private class multButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText(numField.getText());
            } else
            {
                numField.setText(numField.getText() + " * ");
            }
        }
    }

    //this button adds a negative symbol prior to the most recent number
    //it checks for symbols and only places neg sign on most recent number
    //if a number was the last thing typed
    private class posNegButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String value = numField.getText();
            switch (value.length())
            {
                case 0:
                    value = "-";
                    break;
                case 1:
                    value = "-" + value;
                    break;
                default:
                    OUTER:
                    for (int i = value.length() - 1; i >= 1; i--)
                    {
                        if (i == 1)
                        {
                            value = '-' + value;
                        }
                        char c = value.charAt(i - 1);
                        if (!Character.isDigit(value.charAt(i - 1))
                                && Character.isDigit(value.charAt(i)))
                        {
                            switch (c)
                            {
                                case ' ':
                                    value = value.substring(0, i) + '-'
                                            + value.substring(i, value.length());
                                    break OUTER;
                                case '-':
                                    break OUTER;
                                case '(':
                                    value = value.substring(0, i) + '-'
                                            + value.substring(i, value.length());
                                    break OUTER;
                                default:
                                    break;
                            }
                        }
                    }
                    break;
            }
            numField.setText(value);
        }
    }

    //adds decimal symbol, if number is not the last typed thing
    //adds 0. to keep with formating for doubles
    private class pointButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String value = numField.getText();
            if (numField.getText().equals(""))
            {
                numField.setText("0.");
            } else if ((value.charAt(value.length() - 1) == ' '))
            {
                numField.setText(numField.getText() + "0.");
            } else if (Character.isDigit((value.charAt(value.length() - 1))))
            {
                numField.setText(numField.getText() + ".");
            }
        }
    }

    //all following buttons add their corrosponding number
    private class zeroButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText("0");
            } else
            {
                numField.setText(numField.getText() + "0");
            }
        }
    }

    private class oneButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText("1");
            } else
            {
                numField.setText(numField.getText() + "1");
            }
        }
    }

    private class twoButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText("2");
            } else
            {
                numField.setText(numField.getText() + "2");
            }
        }
    }

    private class threeButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText("3");
            } else
            {
                numField.setText(numField.getText() + "3");
            }
        }
    }

    private class fourButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText("4");
            } else
            {
                numField.setText(numField.getText() + "4");
            }
        }
    }

    private class fiveButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText("5");
            } else
            {
                numField.setText(numField.getText() + "5");
            }
        }
    }

    private class sixButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText("6");
            } else
            {
                numField.setText(numField.getText() + "6");
            }
        }
    }

    private class sevenButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText("7");
            } else
            {
                numField.setText(numField.getText() + "7");
            }
        }
    }

    private class eightButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText("8");
            } else
            {
                numField.setText(numField.getText() + "8");
            }
        }
    }

    private class nineButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            if (numField.getText().equals(""))
            {
                numField.setText("9");
            } else
            {
                numField.setText(numField.getText() + "9");
            }
        }
    }

    //deletes the most recent typed item
    private class deleteButtonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String value = numField.getText();
            if (value.charAt(value.length() - 1) == ' ')
            {
                value = value.substring(0, value.length() - 3);
            } else if (!value.equals(""))
            {
                value = value.substring(0, value.length() - 1);
            }
            numField.setText(value);
        }
    }
}
