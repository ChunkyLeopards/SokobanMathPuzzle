import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;


public class MathPanel {
   
   private JPanel problemPanel;
   private JPanel inputPanel;
   private JPanel functionPanel;
   private JPanel buttonPanel;
   private JPanel groupButtonPanel;
   private JPanel symbolButtonPanel;
   private JPanel symbolButtonPanelOptions[];
   private String groupButtonNames[][];
   private String symbolButtonNames[][][];
   private JButton groupButtons[][];
   private JButton symbolButtons[][][];
   private String groupLaTeX[][];
   private String symbolLaTeX[][][];
   private TeXFormula groupForm[][];
   private TeXFormula symbolForm[][][];
   private TeXIcon groupImage[][];
   private TeXIcon symbolImage[][][];
   private JLabel function;
   private String formulaLaTeX[];
   private TeXFormula formula;
   private TeXIcon functionImage;
   private JTextField input;
   private CardLayout symbolOptions;
   
   public JPanel createMath() {
      
      MathTemplateInterpreter puzzle = new MathTemplateInterpreter();
      symbolOptions = new CardLayout();
      problemPanel = new JPanel();
      problemPanel.setLayout(new BoxLayout(problemPanel, BoxLayout.PAGE_AXIS));
      formulaLaTeX = new String[10];
      
      formulaLaTeX[0] = "x = \\frac{-b\\pm\\sqrt{b^2+4ac}}{2a}";
      formulaLaTeX[1] = "\\sum^{50}_{i=0}i^2";
      formulaLaTeX[2] = "\\int^5_0\\frac{x^2}{2}\\frac{dx}{dt}";
      formulaLaTeX[3] = "\\begin{bmatrix}5&7&6&2\\\\9&-2&-6&3\\\\7&-1&2&1\\end{bmatrix}\\begin{bmatrix}2&4\\\\-5&7\\\\2&-8\\\\3&0\\end{bmatrix}";
      formulaLaTeX[4] = "\\lim_{x\\to-0}\\tan\\left(\\frac{\\pi}{2}\\right)";
      formulaLaTeX[5] = "\\log_6{18^x}";
      formulaLaTeX[6] = "\\begin{array}{c|c|c|c}\\text{name}&\\text{lowercase}&\\text{uppercase}&\\text{variable}\\\\\\hline\\text{Alpha}&\\alpha&\\Alpha\\\\\\text{Beta}&\\beta&\\Beta\\\\\\text{Gamma}&\\gamma&\\Gamma\\\\\\text{Delta}&\\delta&\\Delta"/*\\\\\\text{Epsilon}&\\epsilon&\\Epsilon&\\varepsilon\\\\\\text{Zeta}&\\zeta&\\Zeta\\\\\\text{Eta}&\\eta&\\Eta\\\\\\text{Theta}&\\theta&\\Theta&\\vartheta\\\\\\text{Iota}&\\iota&\\Iota\\\\\\text{Omicron}&\\omicron&\\Omicron\\\\\\text{Pi}&\\pi&\\Pi\\\\\\text{Rho}&\\rho&\\Rho&\\varrho*/ + "\\\\\\text{Sigma}&\\sigma&\\Sigma"/*\\\\\\text{Tau}&\\tau&\\Tau\\\\\\text{Upsilon}&\\upsilon&\\Upsilon\\\\\\text{Phi}&\\phi&\\Phi&\\varphi\\\\\\text{Chi}&\\chi&\\Chi\\\\\\text{Psi}&\\psi&\\Psi*/ + "\\\\\\text{Omega}&\\omega&\\Omega\\end{array}";
      formulaLaTeX[7] = "\\text{3 }\\text{random }\\text{numbers!}\\\\" + (int)(Math.random() * 100) + "\\\\" + (int)(Math.random() * 100) + "\\\\" + (int)(Math.random() * 100);
      formulaLaTeX[8] = "P\\land(Q\\lor R)\\equiv(P\\land Q)\\lor(P\\land R)";
      formulaLaTeX[9] = "f(x)\\geq g(x) - f(x)\\geq h(x) - g(x)";
      
      formula = new TeXFormula(formulaLaTeX[(int)(Math.random() * 10)]);
      functionImage = formula.new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY)
                             .setSize(30)
                             .setFGColor(Color.white)
                             .setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER)
                             .setIsMaxWidth(true).setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f)
                             .build();
      functionPanel = new JPanel();
      functionPanel.setBackground(Color.black);
      function = new JLabel(functionImage);
      function.setMaximumSize(new Dimension(100,300));
      function.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
      functionPanel.add(function);
      problemPanel.add(functionPanel);
      return problemPanel;
        
   }
   
   public JPanel createInput() {
      
      inputPanel = new JPanel();
      inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
      input = new JTextField();
      buttonPanel = new JPanel();
      buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
      buttonPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
      groupButtonPanel = new JPanel();
      buttonPanel.add(groupButtonPanel);
      buttonPanel.add(Box.createRigidArea(new Dimension(15,0)));
      groupButtonPanel.setLayout(new GridLayout(3, 4));
      symbolButtonPanel = new JPanel();
      buttonPanel.add(symbolButtonPanel);
      symbolButtonPanel.setLayout(symbolOptions);
      groupLaTeX = new String[3][4];
      symbolLaTeX = new String[12][2][5];
      groupForm = new TeXFormula[3][4];
      symbolForm = new TeXFormula[12][2][5];
      groupImage = new TeXIcon[3][4];
      symbolImage = new TeXIcon[12][2][5];
      groupButtons = new JButton[3][4];
      symbolButtons = new JButton[12][2][5];
      groupButtonNames = new String[3][4];
      symbolButtonNames = new String[12][2][5];
      symbolButtonPanelOptions = new JPanel[12];

      groupButtonNames[0][0] = "Greek Letters";
      groupButtonNames[0][1] = "Functions";
      groupButtonNames[0][2] = "Set Theory Symbols";
      groupButtonNames[0][3] = "Relational Operators";
      groupButtonNames[1][0] = "Logic Symbols";
      groupButtonNames[1][1] = "Trigonometric Symbols";
      groupButtonNames[1][2] = "Geometric Symbols";
      groupButtonNames[1][3] = "Number Sets";
      groupButtonNames[2][0] = "Operations";
      groupButtonNames[2][1] = "Constants";
      groupButtonNames[2][2] = "Statistics";
      groupButtonNames[2][3] = "Combinatorics";
      
      groupLaTeX[0][0] = "\\alpha"; //Greek letters
      groupLaTeX[0][1] = "\\int"; //functions
      groupLaTeX[0][2] = "\\cap"; //sets
      groupLaTeX[0][3] = "\\neq"; //relational
      groupLaTeX[1][0] = "\\wedge"; //logic
      groupLaTeX[1][1] = "\\sin"; //trigonometry
      groupLaTeX[1][2] = "\\parallel"; //geometry
      groupLaTeX[1][3] = "\\mathbb{R}"; //number sets
      groupLaTeX[2][0] = "\\pm"; //operations
      groupLaTeX[2][1] = "\\pi"; //constants
      groupLaTeX[2][2] = "\\%"; //statistics
      groupLaTeX[2][3] = "!"; //combinatorics
      
      for (int i = 0; i < groupLaTeX.length; i++) {
         
         for (int j = 0; j < groupLaTeX[0].length; j++) {
            
            groupForm[i][j] = new TeXFormula(groupLaTeX[i][j]);
            groupImage[i][j] = groupForm[i][j].new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY)
                                                                   .setSize(15)
                                                                   .setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER)
                                                                   .setIsMaxWidth(true).setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f)
                                                                   .build();
            groupButtons[i][j] = new JButton();
            groupButtons[i][j].setIcon(groupImage[i][j]);
            groupButtons[i][j].setToolTipText(groupButtonNames[i][j]);
            groupButtons[i][j].setActionCommand(groupButtonNames[i][j]);
            
            groupButtons[i][j].addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent arg0) {
                  
                  symbolOptions.show(symbolButtonPanel, arg0.getActionCommand());
                  
               }
               
            });
            
            groupButtonPanel.add(groupButtons[i][j]);
            
         }
            
      }
      
      symbolButtonNames[0][0][0] = "Lowercase Alpha";
      symbolButtonNames[1][0][0] = "Summation";
      symbolButtonNames[2][0][0] = "Subset";
      symbolButtonNames[3][0][0] = "Not Equals";
      symbolButtonNames[4][0][0] = "Negation";
      symbolButtonNames[5][0][0] = "Sin";
      symbolButtonNames[6][0][0] = "Parallel";
      symbolButtonNames[7][0][0] = "Real Numbers";
      symbolButtonNames[8][0][0] = "Plus or Minus";
      symbolButtonNames[9][0][0] = "Pi";
      symbolButtonNames[10][0][0] = "Standard Deviation or Covariance";
      symbolButtonNames[11][0][0] = "Combination";
      
      symbolLaTeX[0][0][0] = "\\alpha";
      symbolLaTeX[1][0][0] = "\\Sigma";
      symbolLaTeX[2][0][0] = "\\subset";
      symbolLaTeX[3][0][0] = "\\neq";
      symbolLaTeX[4][0][0] = "\\neg";
      symbolLaTeX[5][0][0] = "\\sin";
      symbolLaTeX[6][0][0] = "\\parallel";
      symbolLaTeX[7][0][0] = "\\mathbb{R}";
      symbolLaTeX[8][0][0] = "\\pm";
      symbolLaTeX[9][0][0] = "\\pi";
      symbolLaTeX[10][0][0] = "\\sigma";
      symbolLaTeX[11][0][0] = "\\binom";
      
      for (int i = 0; i < symbolLaTeX.length; i++) {
         
         symbolButtonPanelOptions[i] = new JPanel();
         symbolButtonPanelOptions[i].setLayout(new GridLayout(2, 5));
         
         for (int j = 0; j < symbolLaTeX[0].length; j++) {
            
            for(int k = 0; k < symbolLaTeX[0][0].length; k++) {
               
               symbolForm[i][j][k] = new TeXFormula(symbolLaTeX[i][j][k]);
               symbolImage[i][j][k] = symbolForm[i][j][k].new TeXIconBuilder().setStyle(TeXConstants.STYLE_DISPLAY)
                                                                   .setSize(15)
                                                                   .setWidth(TeXConstants.UNIT_PIXEL, 256f, TeXConstants.ALIGN_CENTER)
                                                                   .setIsMaxWidth(true).setInterLineSpacing(TeXConstants.UNIT_PIXEL, 20f)
                                                                   .build();
               symbolButtons[i][j][k] = new JButton();
               symbolButtons[i][j][k].setIcon(symbolImage[i][j][k]);
               symbolButtons[i][j][k].setToolTipText(symbolButtonNames[i][j][k]);
               symbolButtons[i][j][k].setActionCommand(symbolLaTeX[i][j][k]);
               
               symbolButtons[i][j][k].addActionListener(new ActionListener() {

                  @Override
                  public void actionPerformed(ActionEvent arg0) {
                     
                     input.setText(input.getText() + arg0.getActionCommand());
                     
                  }
                  
               });
               
               symbolButtonPanelOptions[i].add(symbolButtons[i][j][k]);
            
            }
            
         }
         
         symbolButtonPanel.add(symbolButtonPanelOptions[i], groupButtons[i / 4][i % 4].getToolTipText());
            
      }
      inputPanel.add(input);
      inputPanel.add(buttonPanel);
      
      return inputPanel;
      
   }

}
