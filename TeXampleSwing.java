import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;


public class TeXampleSwing {
    public static void main(String[] args) {
        String latex[] = new String[10];
        latex[0] = "x = \\frac{-b\\pm\\sqrt{b^2+4ac}}{2a}";
        latex[1] = "\\sum^{50}_{i=0}i^2";
        latex[2] = "\\int^5_0\\frac{x^2}{2}\\frac{dx}{dt}";
        latex[3] = "\\begin{bmatrix}5&7&6&2\\\\9&-2&-6&3\\\\7&-1&2&1\\end{bmatrix}\\begin{bmatrix}2&4\\\\-5&7\\\\2&-8\\\\3&0\\end{bmatrix}";
        latex[4] = "\\lim_{x\\to-0}\\tan(x)";
        latex[5] = "\\log_6{18^x}";
        latex[6] = "\\begin{array}{c|c|c|c}\\text{name}&\\text{lowercase}&\\text{uppercase}&\\text{variable}\\\\\\hline\\text{Alpha}&\\alpha&\\Alpha\\\\\\text{Beta}&\\beta&\\Beta\\\\\\text{Gamma}&\\gamma&\\Gamma\\\\\\text{Delta}&\\delta&\\Delta\\\\\\text{Epsilon}&\\epsilon&\\Epsilon&\\varepsilon\\\\\\text{Zeta}&\\zeta&\\Zeta\\\\\\text{Eta}&\\eta&\\Eta\\\\\\text{Theta}&\\theta&\\Theta&\\vartheta\\\\\\text{Iota}&\\iota&\\Iota\\\\\\text{Omicron}&\\omicron&\\Omicron\\\\\\text{Pi}&\\pi&\\Pi\\\\\\text{Rho}&\\rho&\\Rho&\\varrho\\\\\\text{Sigma}&\\sigma&\\Sigma\\\\\\text{Tau}&\\tau&\\Tau\\\\\\text{Upsilon}&\\upsilon&\\Upsilon\\\\\\text{Phi}&\\phi&\\Phi&\\varphi\\\\\\text{Chi}&\\chi&\\Chi\\\\\\text{Psi}&\\psi&\\Psi\\\\\\text{Omega}&\\omega&\\Omega\\end{array}";
        latex[7] = "\\text{3 }\\text{random }\\text{numbers!}\\\\" + (int)(Math.random() * 100) + "\\\\" + (int)(Math.random() * 100) + "\\\\" + (int)(Math.random() * 100);
        latex[8] = "P\\land(Q\\lor R)\\equiv(P\\land Q)\\lor(P\\land R)";
        latex[9] = "f(x)\\geq g(x) - f(x)\\geq h(x) - g(x)";
        JFrame query = new JFrame();
        query.setLayout(new BoxLayout(query.getContentPane(), BoxLayout.PAGE_AXIS));
        JLabel question = new JLabel();
        question.setText("Choose a formula to display between 1 and 10.");
        query.add(question);
        JTextField answer = new JTextField();
        query.add(answer);
        JButton submit = new JButton("Submit");
        submit.addMouseListener(new MouseListener() {

         @Override
         public void mouseClicked(MouseEvent arg0) {
            // TODO Auto-generated method stub
            query.setVisible(false);
            int number = Integer.parseInt(answer.getText());
            if(number < 1 || number > 10) {
               System.out.println("Error. Number out of range.");
               return;
            }
            TeXFormula formula = new TeXFormula(latex[number - 1]);
            TeXIcon icon = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY)
                           .setSize(30)
                           .setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER)
                           .setIsMaxWidth(true).setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f)
                           .build();

            JFrame frame = new JFrame();
            final JLabel label = new JLabel(icon);
            label.setMaximumSize(new Dimension(100,300));
            label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            frame.getContentPane().add(label);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            
         }

         @Override
         public void mouseEntered(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void mouseExited(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void mousePressed(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }

         @Override
         public void mouseReleased(MouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }
           
        });
        
        query.add(submit);
        query.pack();
        query.setVisible(true);
        
    }

}
