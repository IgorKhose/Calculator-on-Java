import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Calculator {
    public static void main(String[] args){
EventQueue.invokeLater(new Runnable() {
    @Override
    public void run() {
        CalculatorFrame frame =new CalculatorFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
});
    }
}
class CalculatorFrame extends JFrame{
    public CalculatorFrame(){
        setTitle("Calculator");
        CalculatorPanel panel=new CalculatorPanel();
        add(panel);
        pack();

    }
}

class CalculatorPanel extends JPanel{
    public CalculatorPanel(){
        setLayout(new BorderLayout());
        result=0;
        lastCommand="=";
        start=true;
        display=new JButton("0");
        display.setEnabled(false);
        add(display,BorderLayout.NORTH);

        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();

        panel=new JPanel();
        panel.setLayout(new GridLayout(6,3));

        addButton("7", insert);
        addButton("8", insert);
        addButton("9", insert);

        addButton("4", insert);
        addButton("5", insert);
        addButton("6", insert);

        addButton("3", insert);
        addButton("2", insert);
        addButton("1", insert);

        addButton("0", insert);
        addButton("*", command);
        addButton("%",command);

        addButton("/", command);
        addButton("+", command);
        addButton("-", command);

        addButton("CL", command);
        addButton(".", insert);
        addButton("=", command);


        add(panel,BorderLayout.CENTER);

    }
    private void addButton(String label,ActionListener listener){
        JButton button=new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }
    private class InsertAction implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String input = event.getActionCommand();
            if(start) {
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
    }
    private class CommandAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            String command=event.getActionCommand();
            if(start){
                if(command.equals("CL")){
                    display.setText("");
                    start=true;
                }
                else if(command.equals("%")){
                    calcProc(Float.parseFloat(display.getText()));
                    lastCommand=command;
                    start=false;
                }
                else lastCommand=command;
            }
            else{
                calculate(Float.parseFloat(display.getText()));
                lastCommand=command;
                start=true;
            }
        }
    }
    public void calculate(float x){
        if(lastCommand.equals("+")) result +=x;
        else if(lastCommand.equals("-")) result -= x;
        else if(lastCommand.equals("*")) result *= x;
        else if(lastCommand.equals("/")) result /= x;
        else if(lastCommand.equals("=")) result = x;
        display.setText(""+result);


    }
    public void calcProc(float x){
        result= (float) (x*0.01);
        display.setText(""+result);
    }
    private JButton display;
    private JPanel panel;
    private float result;
    private String lastCommand;
    private boolean start;
}
