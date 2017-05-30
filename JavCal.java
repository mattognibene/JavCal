import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavCal extends JFrame {

    private JTextField tfAnswer;
    private JPanel button;
    private JPanel jPanelFuctions;
    private JPanel jPanelSolveFunctions;

    private String firstNum="";
    private String function="";
    private String secondNum="";


    public JavCal(){
        super("JavCal");
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        setSize(300,430);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tfAnswer = new JTextField();
        tfAnswer.setEditable(false);
        tfAnswer.setPreferredSize(new Dimension(290,35));

        button = new JPanel();
        button.setPreferredSize(new Dimension(290,220));
        button.setLayout(new FlowLayout());
        btnNumberHandler btnNumberHandler = new btnNumberHandler();


        for(int i = 7; i >= 1 ; i-=3)
            for(int j = 0; j<3; j++) {
                JButton btnTemp = new JButton(Integer.toString(i + j));
                btnTemp.setPreferredSize(new Dimension(85,50));
                btnTemp.addActionListener(btnNumberHandler);
                button.add(btnTemp);

            }

        JButton btnPlusMinus = new JButton("±");
        btnPlusMinus.setPreferredSize(new Dimension(85,50));
        button.add(btnPlusMinus);

        JButton btnZero = new JButton("0");
        btnZero.setPreferredSize(new Dimension(85,50));
        btnZero.addActionListener(btnNumberHandler);
        button.add(btnZero);

        JButton btnDot = new JButton(".");
        btnDot.setPreferredSize(new Dimension(85,50));
        button.add(btnDot);


        jPanelFuctions = new JPanel();
        jPanelFuctions.setLayout(new FlowLayout());
        jPanelFuctions.setPreferredSize(new Dimension(290,60));
        btnFunctionHandler btnFunctionHandler = new btnFunctionHandler();

        JButton btnPlus = new JButton("+");
        btnPlus.setPreferredSize(new Dimension(63,50));
        btnPlus.addActionListener(btnFunctionHandler);
        jPanelFuctions.add(btnPlus);
        
        JButton btnMinus = new JButton("-");
        btnMinus.setPreferredSize(new Dimension(63,50));
        btnMinus.addActionListener(btnFunctionHandler);
        jPanelFuctions.add(btnMinus);
        
        JButton btnTimes = new JButton("*");
        btnTimes.setPreferredSize(new Dimension(63,50));
        btnTimes.addActionListener(btnFunctionHandler);
        jPanelFuctions.add(btnTimes);
        
        JButton btnDivide = new JButton("/");
        btnDivide.setPreferredSize(new Dimension(63,50));
        btnDivide.addActionListener(btnFunctionHandler);
        jPanelFuctions.add(btnDivide);


        jPanelSolveFunctions = new JPanel();
        jPanelSolveFunctions.setLayout(new FlowLayout());
        jPanelSolveFunctions.setPreferredSize(new Dimension(290,60));
        JButton btnEnter = new JButton("Enter");
        btnEnter.setPreferredSize(new Dimension(85,50));
        btnEnter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!firstNum.equals("")&&!secondNum.equals("")){
                            solve();
                        }
                    }
                }
        );
        jPanelSolveFunctions.add(btnEnter);

        JButton btnClear = new JButton("C");
        btnClear.setPreferredSize(new Dimension(85,50));
        btnClear.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        firstNum="";
                        secondNum="";
                        function="";
                        tfAnswer.setText("");
                    }
                }
        );
        jPanelSolveFunctions.add(btnClear);


        cp.add(tfAnswer);
        cp.add(button);
        cp.add(jPanelFuctions);
        cp.add(jPanelSolveFunctions);

        setVisible(true);
    }

    private void solve(){
        switch (function){
            case "+":
                tfAnswer.setText(Double.toString(Double.parseDouble(firstNum)+Double.parseDouble(secondNum)));
                break;
            case "-":
                tfAnswer.setText(Double.toString(Double.parseDouble(firstNum)-Double.parseDouble(secondNum)));
                break;
            case "/":
                tfAnswer.setText(Double.toString(Double.parseDouble(firstNum)/Double.parseDouble(secondNum)));
                break;
            case "*":
                tfAnswer.setText(Double.toString(Double.parseDouble(firstNum)*Double.parseDouble(secondNum)));
                break;
        }

    }

    private class btnNumberHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(function.equals("")){
                firstNum+=event.getActionCommand();
                tfAnswer.setText(firstNum);
            }else{
                secondNum+=event.getActionCommand();
                tfAnswer.setText(secondNum);
            }

        }
    }
    private class btnFunctionHandler implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                if(function.equals("")&&!firstNum.equals(""))
                    function=e.getActionCommand();

            }
    }

    public static void main(String[] args) {
        new JavCal();
    }
}
