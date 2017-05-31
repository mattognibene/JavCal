import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JavCal extends JFrame {

    private JTextField tfAnswer;
    private JPanel button;
    private JPanel jPanelFuctions;
    private JPanel jPanelSolveFunctions;

    private String firstNum="";
    private String function="";
    private String secondNum="";
    private boolean firstNumFlag;


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
        Action beep = tfAnswer.getActionMap().get(DefaultEditorKit.deletePrevCharAction);
        beep.setEnabled(false);

        tfAnswer.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                        try {
                            Integer.parseInt(Character.toString(e.getKeyChar()));
                            String typed = Character.toString(e.getKeyChar());
                            enterNumber(typed);
                        } catch(Exception ex){
                            String typed = Character.toString(e.getKeyChar());
                            if(typed.equals("+")||typed.equals("-")||typed.equals("*")||typed.equals("/"))
                                enterFunction(typed);
                            else if(typed.equals("."))
                                addDot();
                        }
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode()==8){
                            if(!secondNum.equals("")){
                                secondNum=secondNum.substring(0,secondNum.length()-1);
                                tfAnswer.setText(secondNum);
                            }else if(!function.equals("")){
                                function="";
                            }else if(!firstNum.equals("")){
                                firstNum=firstNum.substring(0,firstNum.length()-1);
                                tfAnswer.setText(firstNum);
                            }
                        }
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {}
                }
        );
        tfAnswer.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!firstNum.equals("") && !secondNum.equals(""))
                            solve();
                    }
                }
        );

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
        btnPlusMinus.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(tfAnswer.getText().equals(firstNum)&&function.equals("")){
                            firstNum= Double.toString(Double.parseDouble(firstNum)*-1);
                            tfAnswer.setText(firstNum);
                        }else if(!function.equals("")){
                            secondNum= Double.toString(Double.parseDouble(secondNum)*-1);
                            tfAnswer.setText(secondNum);
                        }

                    }
                }
        );
        button.add(btnPlusMinus);

        JButton btnZero = new JButton("0");
        btnZero.setPreferredSize(new Dimension(85,50));
        btnZero.addActionListener(btnNumberHandler);
        button.add(btnZero);

        JButton btnDot = new JButton(".");
        btnDot.setPreferredSize(new Dimension(85,50));
        btnDot.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addDot();
                    }
                }
        );
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
                        firstNumFlag=false;
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
        firstNum=tfAnswer.getText();
        function="";
        secondNum="";
        firstNumFlag=true;

    }



    private class btnNumberHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            enterNumber(event.getActionCommand());
        }
    }
    private class btnFunctionHandler implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                enterFunction(e.getActionCommand());
            }
    }

    public void enterNumber(String s){
        if(firstNumFlag){
            firstNum="";
            firstNum+=s;
            secondNum="";
            function="";
            firstNumFlag=false;
            tfAnswer.setText(firstNum);
        }else {
            if (function.equals("")) {
                firstNum += s;
                tfAnswer.setText(firstNum);
            } else {
                secondNum += s;
                tfAnswer.setText(secondNum);
            }
        }
    }

    public void enterFunction(String s){
        if(function.equals("")&&!firstNum.equals("")) {
            firstNumFlag = false;
            function = s;
        }else if(!secondNum.equals("")){
            solve();
            firstNumFlag=false;
            function=s;
        }
    }
    public void addDot(){
        if(tfAnswer.getText().equals(firstNum)&&function.equals("")){
            if(!firstNum.contains(".")){
                firstNum+=".";
                tfAnswer.setText(firstNum);
            }
        }else if(!function.equals("")){
            if(!secondNum.contains(".")) {
                secondNum += ".";
                tfAnswer.setText(secondNum);
            }
        }
    }

    public static void main(String[] args) {
        new JavCal();
    }
}
